source environment-setup.sh
tar -xf $HADOOP_TAR_FILE -C $HADOOP_UNTAR_FOLDER


master=$(cat $MASTERS_FILE)

sed -i -e "s|^export JAVA_HOME=\${JAVA_HOME}|export JAVA_HOME=$JAVA_HOME|g" ${HADOOP_PREFIX}/etc/hadoop/hadoop-env.sh

cat <<EOF > ${HADOOP_PREFIX}/etc/hadoop/core-site.xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://$master:9000</value>
    </property>
</configuration>
EOF

cat <<EOF > ${HADOOP_PREFIX}/etc/hadoop/hdfs-site.xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file://$DATANODE_PATH</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file://$NAMENODE_PATH</value>
    </property> 
</configuration>
EOF


cat <<EOF > ${HADOOP_PREFIX}/etc/hadoop/yarn-site.xml
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
EOF

echo Y | $HADOOP_PREFIX/bin/hdfs namenode -format

echo yes | cp $SLAVES_FILE $HADOOP_PREFIX/etc/hadoop/slaves
echo yes | cp $MASTERS_FILE $HADOOP_PREFIX/etc/hadoop/masters

