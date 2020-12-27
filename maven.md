
This error occurs on Ubuntu 18.04 with AdoptJDK 11, with Maven installed from Ubuntu repository.


In order to avoid below error (occurring at each Maven execution), Maven must be downloaded from its website.

WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release



# Download maven 3.6.3 (always ensure this is the latest version)
wget https://www-us.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -P /tmp

# Untar downloaded file to /opt
sudo tar xf /tmp/apache-maven-*.tar.gz -C /opt

# Install the alternative version for the mvn in your system
sudo update-alternatives --install /usr/bin/mvn mvn /opt/apache-maven-3.6.3/bin/mvn 363

# Check if your configuration is ok. You may use your current or the 3.6.3 whenever you wish, running the command below.
sudo update-alternatives --config mvn


Source:
 - https://stackoverflow.com/questions/55492394/maven-an-illegal-reflective-access-operation-has-occurred