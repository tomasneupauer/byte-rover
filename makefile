all: main archive

main: src/AppView.java
	javac -d build/ -cp ".:lib/flatlaf.jar" src/*.java

archive: build/AppView.class
	jar -cfm release/byterover.jar src/manifest.mf -C build/ .

clear:
	rm build/*
