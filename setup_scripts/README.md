# Setup script
This is a script for setting up a hadoop in multi node environment.
In order to use it, you should modify 3 files:
- setup-environment.sh
- masters.txt
- slaves.txt

## Files:
- setup-environment.sh
    - Declares variables used in other scripts and hadoop.
- hadoop-setup.sh
    - Sets up the hadoop environment based on configurations in setup-environment.sh
- hadoop-start.sh
    - Starts hadoop. More specifically; starts hdfs and yarn
- hadoop-stop.sh
    - Stops hadoop. More specifically; stops hdfs and yarn
- hadoop-cleanup.sh
    - Removes the hadoop files from the nodes it has been copied to.
- download-hadoop.sh
    - Downloads hadoop in a folder specified in setup-environment.sh
- copy-to-hdfs.sh
    - Copies the specified file into the hadoop hdfs
    - Usage: ```. copy-to-hdfs.sh filepath_on_local_fs path_on_hdfs```
- copy-to-nodes.sh
    - Copies the hadoop files to remote nodes. 
- masters.txt
    - Hadoop masters file. Each address has to be specified in its own line.
- slaves.txt
    - Hadoop slaves file. Each address has to be specified in its own line.

### FAQ
* How to run the hadoop in a multi clustered mode?
    1. Make sure you can freely ssh into your remote nodes specified in slaves file
    2. configure /etc/hosts file appropriately in every node
    3. Set environment variables in environment-setup.sh
    4. Set masters.txt file
    5. Set slaves.txt file
    6. Invoke download_hadoop.sh
    7. Invoke hadoop-setup.sh
    8. Invoke copy-to-nodes.sh
    9. Invoke hadoop-start.sh

* How to copy files into hdfs?
    1. Make sure the hadoop is running (hadoop-start.sh)
    2. Invoke copy-to-hdfs.sh with 2 arguments: 
        - filepath on local filesystem
        - target path on hdfs


