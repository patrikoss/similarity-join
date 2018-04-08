source environment-setup.sh

cmd1="rm -rf $DATANODE_PATH"
cmd2="rm -rf $NAMENODE_PATH"
cmd3="rm -rf $HADOOP_PREFIX"

eval $cmd1
eval $cmd2
eval $cmd3
while read user_address; do
	# https://unix.stackexchange.com/questions/107800/using-while-loop-to-ssh-to-multiple-servers
	ssh $user_address $cmd1 < /dev/null
        ssh $user_address $cmd2 < /dev/null
	ssh $user_address $cmd3 < /dev/null
done < $SLAVES_FILE
