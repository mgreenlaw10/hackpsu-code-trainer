##########################
#        SHELL
##########################
SHELL := cmd
.SHELLFLAGS := /C

##########################
#         VARS
##########################

# ---- File structure ----
SRC_DIR     := src
GEN_DIR     := $(SRC_DIR)/gen
BIN         := bin
LIB         := lib
RES         := res

# Sources (top-level, one- and two-level subdirs)
SRCS        := $(wildcard $(SRC_DIR)/*.java) \
               $(wildcard $(SRC_DIR)/*/*.java) \
               $(wildcard $(SRC_DIR)/*/*/*.java) \
               $(wildcard $(SRC_DIR)/*/*/*/*.java)
# Generated sources after ANTLR runs
GEN_SRCS    := $(wildcard $(GEN_DIR)/*.java) \
               $(wildcard $(GEN_DIR)/*/*.java)
ALL_SRCS    := $(SRCS) $(GEN_SRCS)

# ---- ANTLR ----
ANTLR_VER   ?= 4.13.2
ANTLR_JAR   ?= $(LIB)/antlr-$(ANTLR_VER)-complete.jar
GRAMMARS    = $(wildcard $(SRC_DIR)/grammar/*.g4)
GEN_STAMP   = $(GEN_DIR)/.generated.stamp
# Windows-style glob for IF EXIST (cmd prefers backslashes)
GRAMMAR_GLOB_WIN := $(SRC_DIR)\grammar\*.g4

# ---- Java ----
CFLAGS      ?= -Xlint:unchecked

# Prefer JAVA_HOME if available; otherwise rely on PATH
ifdef JAVA_HOME
  JAVA  ?= "$(JAVA_HOME)/bin/java"
  JAVAC ?= "$(JAVA_HOME)/bin/javac"
  JARXZ ?= "$(JAVA_HOME)/bin/jar"
else
  JAVA  ?= java
  JAVAC ?= javac
  JARXZ ?= jar
endif

# ---- JavaFX (optional) ----
# Set JFX_SDK to your JavaFX SDK root (folder that contains /lib).
# Example: JFX_SDK=lib/javafx-sdk-25
JFX_SDK     ?= $(LIB)/javafx-sdk-25
JFX_LIB     := $(JFX_SDK)/lib
JFX_MODS    ?= javafx.controls,javafx.fxml
# Only add JavaFX flags if the SDK lib directory exists
ifneq ("$(wildcard $(JFX_LIB))","")
  JFX_FLAGS := --module-path "$(JFX_LIB)" --add-modules $(JFX_MODS)
else
  JFX_FLAGS :=
endif

# ---- RichTextFX ----
RICH_JAR := $(LIB)/richtextfx-fat-0.11.6.jar

# ---- App ----
MAIN_CLASS  ?= Main
JAR_NAME    ?= app.jar

# ---- Classpath separator ----
ifeq ($(OS),Windows_NT)
  SEP := ;
else
  SEP := :
endif

# ---- Classpath ----
# Runtime classpath (includes bin for already-compiled classes)
CP := $(BIN)$(SEP)$(ANTLR_JAR)$(SEP)$(RICH_JAR)
# Compilation classpath: do NOT include $(BIN) to avoid stale .class files
COMPILE_CP := $(ANTLR_JAR)$(SEP)$(RICH_JAR)

##########################
#         RULES
##########################

# Default: generate + compile + run
all: run

# Ensure output dirs exist (cmd-friendly)
$(BIN) $(GEN_DIR):
	@if not exist "$@" mkdir "$@"

# 1) Generate ANTLR sources when grammars (or the tool jar) change
gen: $(GEN_STAMP)

$(GEN_STAMP): $(GRAMMARS) $(ANTLR_JAR) | $(GEN_DIR)
	@if not exist "$(ANTLR_JAR)" (echo ERROR: Missing "$(ANTLR_JAR)". Place antlr-$(ANTLR_VER)-complete.jar in lib\ & exit /b 1)
	@if exist "$(GRAMMAR_GLOB_WIN)" ( \
	  echo [antlr] generating Java from grammars & \
	  $(JAVA) -jar "$(ANTLR_JAR)" -Dlanguage=Java -visitor -listener -o "$(GEN_DIR)" $(SRC_DIR)/grammar/*.g4 \
	) else ( \
	  echo [antlr] no grammars found, skipping \
	)
	@echo stamp> "$(GEN_STAMP)"



# 2) Compile (depends on generated sources)
compile: gen | $(BIN)
	@if not exist "$(RICH_JAR)" (echo ERROR: Missing "$(RICH_JAR)". Download richtextfx-fat-0.11.6.jar and place it in lib\ & exit /b 1)
	@echo [javac] compiling sources into $(BIN)
	$(JAVAC) $(CFLAGS) $(JFX_FLAGS) -cp "$(COMPILE_CP)" -d "$(BIN)" $(ALL_SRCS)

# 3) Run (add ANTLR and RichTextFX jars to runtime classpath)
run: compile
	@echo [java] running $(MAIN_CLASS)
	$(JAVA) $(JFX_FLAGS) --enable-native-access javafx.graphics -cp "$(CP)" $(MAIN_CLASS)

# 4) Package jar (no JavaFX runtime bundling)
jar: compile
	@echo [jar] creating $(JAR_NAME)
	cd "$(BIN)" && $(JARXZ) --create --file="..\$(JAR_NAME)" --main-class=$(MAIN_CLASS) -C . .

# Utilities
print-vars:
	@echo JAVA=$(JAVA)
	@echo JAVAC=$(JAVAC)
	@echo JAVA_HOME=$(JAVA_HOME)
	@echo JFX_SDK=$(JFX_SDK)
	@echo JFX_FLAGS=$(JFX_FLAGS)
	@echo CLASSPATH_SEP=$(SEP)
	@echo CP=$(CP)
	@echo ALL_SRCS (first 5 shown):
	@for %A in ($(ALL_SRCS)) do @echo    %A & goto :afterSrcs
	@:afterSrcs

# Clean
clean:
	@echo [clean] removing $(BIN)
	@if exist "$(BIN)" rmdir /s /q "$(BIN)"
	@echo [clean] removing generated $(GEN_DIR)
	@if exist "$(GEN_DIR)" rmdir /s /q "$(GEN_DIR)"
	@echo [clean] removing stamp
	@if exist "$(GEN_STAMP)" del /q "$(GEN_STAMP)"
