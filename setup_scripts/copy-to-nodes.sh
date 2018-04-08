source environment-setup.sh

while read user_address; do
        ssh $user_address "mkdir -p $HADOOP_PREFIX" < /dev/null
        rsync -r $HADOOP_PREFIX/ $user_address:$HADOOP_PREFIX
done < $SLAVES_FILE

