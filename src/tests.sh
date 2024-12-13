if [ -z "$1" ]; then
    echo "Missing test file"
    exit 1
fi

FILE="$1"

javac -d bin app/*.java
javac -d bin -cp .:bin:junit-4.13.2.jar tst/"$FILE".java
java -cp .:junit-4.13.2.jar:bin:hamcrest-core-1.3.jar org.junit.runner.JUnitCore tst."$FILE"

exit 0