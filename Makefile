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
               $(wildcard $(SRC_DIR)/*/*/*.java)
# Generated sources after ANTLR runs
GEN_SRCS    := $(wildcard $(GEN_DIR)/*.java) \
               $(wildcard $(GEN_DIR)/*/*.java)
ALL_SRCS    := $(SRCS) $(GEN_SRCS)

# ---- ANTLR ----
ANTLR_VER   ?= 4.13.2
ANTLR_JAR   ?= $(LIB)/antlr-$(ANTLR_VER)-complete.jar
GRAMMARS    := $(wildcard $(SRC_DIR)/grammar/*.g4)
GEN_STAMP   := $(GEN_DIR)/.generated.stamp

# ---- Java ----
CFLAGS      ?= -Xlint:unchecked

# Prefer JAVA_HOME if available; otherwise rely on PATH
ifdef JAVA_HOME
  JAVA  ?= "$(JAVA_HOME)/bin/java"
  JAVAC ?= "$(JAVA_HOME)/bin/javac"
else
  JAVA  ?= java
  JAVAC ?= javac
endif

# ---- JavaFX (optional) ----
# Set JFX_SDK to your JavaFX SDK root (the folder that contains /lib).
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

# ---- App ----
MAIN_CLASS  ?= Main
JAR_NAME    ?= app.jar

# ---- OS-specific separators/commands ----
ifeq ($(OS),Windows_NT)
  SEP := ;
else
  SEP := :
endif

MKDIR_P     := mkdir -p
RM_RF       := rm -rf

##########################
#         RULES
##########################

# Default: generate + compile + run
all: run

# Ensure output dirs exist
$(BIN) $(GEN_DIR):
	$(MKDIR_P) "$@"

# 1) Generate ANTLR sources when grammars change
gen: $(GEN_STAMP)

$(GEN_STAMP): $(GRAMMARS) $(ANTLR_JAR) | $(GEN_DIR)
	@echo [antlr] generating Java from grammars: $(notdir $(GRAMMARS))
	$(JAVA) -jar "$(ANTLR_JAR)" \
		-Dlanguage=Java \
		-visitor -listener \
		-o "$(GEN_DIR)" \
		$(GRAMMARS)
	@echo stamp > "$(GEN_STAMP)"

# 2) Compile (depends on generated sources)
compile: gen | $(BIN)
	@echo [javac] compiling sources into $(BIN)
	$(JAVAC) \
		$(CFLAGS) \
		$(JFX_FLAGS) \
		-cp "$(BIN)$(SEP)$(ANTLR_JAR)" \
		-d "$(BIN)" $(ALL_SRCS)

# 3) Run (add ANTLR jar to runtime classpath)
run: compile
	@echo [java] running $(MAIN_CLASS)
	$(JAVA) \
		$(JFX_FLAGS) \
		--enable-native-access javafx.graphics \
		-cp "$(BIN)$(SEP)$(ANTLR_JAR)" $(MAIN_CLASS)

# 4) Package jar (no JavaFX runtime bundling)
jar: compile
	@echo [jar] creating $(JAR_NAME)
	cd "$(BIN)" && jar --create --file="../$(JAR_NAME)" --main-class=$(MAIN_CLASS) -C . .

# Utilities
print-vars:
	@echo JAVA=$(JAVA)
	@echo JAVAC=$(JAVAC)
	@echo JAVA_HOME=$(JAVA_HOME)
	@echo JFX_SDK=$(JFX_SDK)
	@echo JFX_FLAGS=$(JFX_FLAGS)
	@echo CLASSPATH_SEP=$(SEP)

.PHONY: clean veryclean gen compile run jar print-vars
clean:
	@echo [clean] removing build outputs
	$(RM_RF) "$(BIN)"

veryclean: clean
	@echo [clean] removing generated sources
	$(RM_RF) "$(GEN_DIR)"
