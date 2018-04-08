# Setup script
This is a script for setting up a hadoop in multi node environment.
In order to use it, you should modify 3 files:
- setup-environment.sh
- masters.txt
- slaves.txt

## Files:
0) setup-environment.sh
Declares variables used in other scripts and hadoop.

1) hadoop-setup.sh
Sets up the hadoop environment based on configurations in setup-environment.sh

2) hadoop-start.sh
Starts hadoop. More specifically; starts hdfs and yarn

3) hadoop-stop.sh
Stops hadoop. More specifically; stops hdfs and yarn

4) hadoop-cleanup.sh
Removes the hadoop files from the nodes it has been copied to.

5) download-hadoop.sh
Downloads hadoop in a folder specified in setup-environment.sh

6) copy-to-hdfs.sh
Copies the specified file into the hadoop hdfs
Usage:
. copy-to-hdfs.sh filepath_on_local_fs path_on_hdfs

*7) copy-to-nodes.sh
Copies the hadoop files to remote nodes. 
This script is unnecessary for students.mimuw.edu.pl

8) masters.txt
Hadoop masters file. Each address has to be specified in its own line.

9) slaves.txt
Hadoop slaves file. Each address has to be specified in its own line.

### FAQ
i) How to run the hadoop in a multi clustered mode?
    - Make sure you can freely ssh into your remote nodes specified in slaves file
    - configure /etc/hosts file appropriately in every node
    - Set environment variables in environment-setup.sh
    - Set masters.txt file
    - Set slaves.txt file
    - Invoke download_hadoop.sh
    - Invoke hadoop-setup.sh
    - Invoke copy-to-nodes.sh
    - Invoke hadoop-start.sh

 ii) How to copy files into hdfs?
    - Make sure the hadoop is running (hadoop-start.sh)
    - Invoke copy-to-hdfs.sh with 2 arguments: file_on_local_fs path_on_hdfs


