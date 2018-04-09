# set environment file
ENV_FILE=../setup_scripts/environment-setup.sh

source $ENV_FILE

export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar

operators=( "LSH" )

for operator in "${operators[@]}"
do
    echo $operator
    $HADOOP_PREFIX/bin/hadoop com.sun.tools.javac.Main *.java
    jar cf $operator.jar *.class
    echo "Run with command:"
    echo "$HADOOP_PREFIX/bin/hadoop jar $operator.jar Main /inputfolder /outputfolder"

done


