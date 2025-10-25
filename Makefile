# ##########################
# #		   VARS          #
# ##########################

# # FILE STRUCTURE
# SRCS 		:= $(wildcard src/*/*.java) $(wildcard src/*/*/*.java) src/Main.java
# LIB 		:= lib
# BIN 		:= bin
# RES			:= res

# # ANTLR
# ANTLR_VER  := 4.13.2
# ANTLR_JAR  := lib/antlr-$(ANTLR_VER)-complete.jar
# GRAMMARS := $(wildcard src/grammar/*.g4)
# GEN_DIR    := src/gen
# GEN_STAMP  := $(GEN_DIR)/.generated.stamp
# CLASS_DIR  := bin

# # JAVA
# CFLAGS 		:= -Xlint:unchecked

# # JDK
# JDK 		:= C:/JDK/jdk-25
# JAVA 		:= $(JDK)/bin/java
# JAVAC 		:= $(JDK)/bin/javac

# # JAVAFX

# JFX_SDK 	:= $(LIB)/javafx-sdk-25
# JFX_LIB   	:= $(JFX_SDK)/lib
# JFX_MODS 	:= javafx.controls,javafx.fxml

# ##########################
# #		  RULES          #
# ##########################

# # Compile and run
# all: run

# compile: 
# 	@echo COMPILING from $(JAVAC)...
# 	@if not exist "$(BIN)" mkdir "$(BIN)"
# 	$(JAVAC)\
# 		$(CFLAGS)\
# 		--module-path $(JFX_LIB)\
# 		--add-modules $(JFX_MODS)\
# 		-d $(BIN) $(SRCS)

# # enable-native-access -> Allow JFX to use restricted methods from native interface
# run: compile
# 	@echo RUNNING from $(JAVA)...
# 	$(JAVA)\
# 		--module-path $(JFX_LIB)\
# 		--add-modules $(JFX_MODS)\
# 		--enable-native-access javafx.graphics\
# 		--class-path $(BIN);$(ANTLR_JAR) Main

# # Wipe all of /bin
# #.PHONY: clean
# #clean:
# #	rmdir /S /Q bin

##########################
#         VARS           #
##########################

# FILE STRUCTURE
SRCS        := $(wildcard src/*.java) \
               $(wildcard src/*/*.java) \
               $(wildcard src/*/*/*.java)
GEN_DIR     := src/gen
GEN_STAMP   := $(GEN_DIR)/.generated.stamp
# Include generated sources (after gen)
GEN_SRCS    := $(wildcard $(GEN_DIR)/*.java) \
               $(wildcard $(GEN_DIR)/*/*.java)
ALL_SRCS    := $(SRCS) $(GEN_SRCS)

LIB         := lib
BIN         := bin
RES         := res

# ANTLR
ANTLR_VER   := 4.13.2
ANTLR_JAR   := $(LIB)/antlr-$(ANTLR_VER)-complete.jar
GRAMMARS    := $(wildcard src/grammar/*.g4)

# JAVA
CFLAGS      := -Xlint:unchecked

# JDK
JDK         := C:/JDK/jdk-25
JAVA        := $(JDK)/bin/java
JAVAC       := $(JDK)/bin/javac

# JAVAFX
JFX_SDK     := $(LIB)/javafx-sdk-25
JFX_LIB     := $(JFX_SDK)/lib
JFX_MODS    := javafx.controls,javafx.fxml

##########################
#         RULES          #
##########################

# Default: generate + compile + run
all: run

# 1) Generate ANTLR sources when grammars change
gen: $(GEN_STAMP)

$(GEN_STAMP): $(GRAMMARS) $(ANTLR_JAR)
	@echo [antlr] generating Java from grammars: $(notdir $(GRAMMARS))
	@if not exist "$(GEN_DIR)" mkdir "$(GEN_DIR)"
	$(JAVA) -jar "$(ANTLR_JAR)"\
		-Dlanguage=Java\
		-visitor -listener\
		-o "$(GEN_DIR)"\
		$(GRAMMARS)
	@echo stamp> "$(GEN_STAMP)"

# 2) Compile (depends on generated sources)
compile: gen
	@echo COMPILING from $(JAVAC)...
	@if not exist "$(BIN)" mkdir "$(BIN)"
	$(JAVAC)\
		$(CFLAGS)\
		--module-path "$(JFX_LIB)"\
		--add-modules $(JFX_MODS)\
		-cp "$(BIN);$(ANTLR_JAR)"\
		-d "$(BIN)" $(ALL_SRCS)

# 3) Run (add ANTLR jar to runtime classpath)
run: compile
	@echo RUNNING from $(JAVA)...
	$(JAVA)\
		--module-path "$(JFX_LIB)"\
		--add-modules $(JFX_MODS)\
		--enable-native-access javafx.graphics\
		-cp "$(BIN);$(ANTLR_JAR)" game.Engine

# Clean
.PHONY: clean
clean:
	@echo Cleaning ...
	@if exist "$(BIN)" rmdir /S /Q "$(BIN)"
	@if exist "$(GEN_DIR)" rmdir /S /Q "$(GEN_DIR)"
