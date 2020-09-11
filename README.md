
```shell script
rm -f build
mkdir build
# find . -name *.class -exec rm {} +
cd /src
javac -d ../build com/estudoLista/Main.java
cd ../build
java com.estudoLista.Main
```

```shell script
cd /build
jar cfm output.jar ../src/META-INF/MANIFEST.MF ./com
java -jar output.jar
```