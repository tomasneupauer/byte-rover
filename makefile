all: main archive

main: src/Main.java
	javac -d build/ src/Main.java

archive: build/Main.class
	jar -cfe release/byterover.jar Main -C build/ .
