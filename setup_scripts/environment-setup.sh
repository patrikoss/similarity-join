# path to your /home/user directory
USER_HOME=/home/hadoopuser

# path to slaves file
export SLAVES_FILE=$USER_HOME/eclipse-workspace/LSH/setup_scripts/slaves.txt

# path to masters file
export MASTERS_FILE=$USER_HOME/eclipse-workspace/LSH/setup_scripts/masters.txt


### Rest of the paths can be left as default ###


# path to default hadoop folder on local filesystems
export HADOOP_PREFIX=$HADOOP_UNTAR_FOLDER/hadoop-2.8.3

# path to folder where the information about the namenode will be stored
export NAMENODE_PATH=${HADOOP_PREFIX}/namenode

# path to folder where the information about the datanode will be stored
export DATANODE_PATH=/tmp/datanode

# path to folder where the hadoop should be untarred
export HADOOP_UNTAR_FOLDER=$USER_HOME/hadoop/cluster_distr

#export JAVA_HOME=/usr
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

# path to folder where .tar file of hadoop is stored
export HADOOP_TAR_FOLDER=$USER_HOME/hadoop/tools

# path to .tar file of hadoop
export HADOOP_TAR_FILE=$HADOOP_TAR_FOLDER/hadoop-2.8.3.tar.gz

# some default options for hadoop
export HADOOP_OPTS=-Djava.net.preferIPv4Stack=true

export HADOOP_DOWNLOAD_URL=http://ftp.man.poznan.pl/apache/hadoop/common/hadoop-2.8.3/hadoop-2.8.3.tar.gz
