source environment-setup.sh

if [ $# -ne 2 ]; then
    echo "Usage: $0 input_file hdfs_filepath"
else
    echo "Copying $1 to $2 on hdfs"
    $HADOOP_PREFIX/bin/hdfs dfs -put $1 $2
fi
