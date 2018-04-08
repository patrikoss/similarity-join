source environment-setup.sh

if [ $# -ne 1 ]; then
    echo "Usage: $0 command"
else
    echo "Executing command $1"
    eval $HADOOP_PREFIX/$1
fi


