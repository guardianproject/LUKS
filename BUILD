/*
Please install the following prerequisites (instructions for each follows):
	Android OS SDK: http://source.android.com/download
	droid-wrapper: http://github.com/tmurakam/droid-wrapper
	libgpg-error: http://www.gnupg.org/related_software/libgpg-error/index.en.html
	libgcrypt: http://directory.fsf.org/project/libgcrypt/
	lvm2: http://sourceware.org/lvm2/
	POPT: http://freshmeat.net/projects/popt/

Install and prepare the Android OS SDK ( http://source.android.com/download )
on Debian or Ubuntu:
*/

	sudo apt-get install git-core gnupg sun-java5-jdk flex bison gperf \
		libsdl-dev libesd0-dev libwxgtk2.6-dev build-essential zip \
		curl libncurses5-dev zlib1g-dev valgrind libtool automake \
		ruby subversion
	update-java-alternatives -s java-1.5.0-sun

	curl http://android.git.kernel.org/repo >~/bin/repo
	chmod a+x ~/bin/repo

	mkdir ~/mydroid
	cd ~/mydroid

	repo init -u git://android.git.kernel.org/platform/manifest.git
	repo sync

	# Paste in key from http://source.android.com/download next...
	gpg --import

	cd ~/mydroid

	# This takes a long while...
	make

##Install droid-wrapper:

	cd /tmp
	git clone git://github.com/tmurakam/droid-wrapper.git
	cd droid-wrapper
	sudo make install

##Now setup the build variables:

	export DROID_ROOT=~/mydroid
	export DROID_TARGET=generic

//Now compile libgpg-error:

	//it is sometimes good to ensure you have the package available on your dev system
	sudo apt-get install libgpg-error-dev

	//now we will pull down the source and built it for Android
	mkdir $DROID_ROOT/external/libgpg-error
	cd $DROID_ROOT/external/libgpg-error
	wget ftp://ftp.gnupg.org/gcrypt/libgpg-error/libgpg-error-1.10.tar.bz2
	// TODO verify .sig from ftp://ftp.gnupg.org/gcrypt/libgpg-error/libgpg-error-1.10.tar.bz2.sig	

	bzcat libgpg-error-1.10.tar.bz2 | tar xv
	cd libgpg-error-1.10
	./autogen.sh
	CC=droid-gcc LD=droid-ld ./configure --enable-static --host=arm-none-linux-gnueabi
	make
	ls -l src/.libs/libgpg-error.a //check the file is there and built properly	
	cp src/.libs/libgpg-error.a $DROID_ROOT/out/target/product/generic/obj/lib

//now build libgcrypt
	
	//it is sometimes good to ensure you have the package available on your dev system
	sudo apt-get install libgcrypt-dev

	//now we will pull down the source and built it for Android
	mkdir $DROID_ROOT/external/libgcrypt
	cd $DROID_ROOT/external/libgcrypt
	wget ftp://ftp.gnupg.org/gcrypt/libgcrypt/libgcrypt-1.4.6.tar.bz2
	bzcat libgcrypt-1.4.6.tar.bz2 | tar xv
	cd libgcrypt-1.4.6
	./autogen.sh
	CC=droid-gcc LD=droid-ld ./configure --enable-static --host=arm-none-linux-gnueabi	

	//now we have to manually patch two files to point to the correct <sys/select.h> header
	//in the following files, add: #include <sys/select.h>
	//in with the other includes //TODO automate this with sed or via a patch file

	//edit this file and add the include to it: 
	vim src/mpi.h
	//then edit this file and add the include to it:
	vim src/g10lib.h

	//now we can build
	make

	//at the end of the build, the tests scripts will fail due to cross compile include issues.
	//This is okay for now, as the lib should be built

	ls src/.libs/libgcrypt.a
	cp src/.libs/libgcrypt.a $DROID_ROOT/out/target/product/generic/obj/lib

//Then, get the necessary pre-built ARM libs and header files from various RPMs:

	cd /tmp
	export SOURCE_URL=http://ftp.linux.org.uk/pub/linux/arm/fedora/pub/fedora/linux/releases/11/Everything/arm/os/Packages/

	export FILE=e2fsprogs-devel-1.41.3-2.fc11.armv5tel.rpm
	wget $SOURCE_URL/$FILE
	mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
	cp root/usr/lib/* $DROID_ROOT/out/target/product/generic/obj/lib
	rm -rf root
	rm $FILE

//Popt can be retrieved here pre-built, or you can build from source (info is later in this file)
	export FILE=popt-static-1.13-5.fc11.armv5tel.rpm
	wget $SOURCE_URL/$FILE
	mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
	cp root/usr/lib/* $DROID_ROOT/out/target/product/generic/obj/lib
	rm -rf root
	rm $FILE

	export FILE=popt-devel-1.13-5.fc11.armv5tel.rpm
	wget $SOURCE_URL/$FILE
	mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
	cp root/usr/include/* $DROID_ROOT/out/target/product/generic/obj/include
	rm -rf root
	rm $FILE

//just get the header files from libgcrypt - not sure this is necessary since we built from source
	export FILE=libgcrypt-devel-1.4.4-4.fc11.armv5tel.rpm
	wget $SOURCE_URL/$FILE
	mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
	cp root/usr/include/* $DROID_ROOT/out/target/product/generic/obj/include
	rm -rf root
	rm $FILE

//Now we have to get LVM2

	mkdir $DROID_ROOT/external/lvm2
        cd $DROID_ROOT/external/lvm2
	wget ftp://sources.redhat.com/pub/lvm2/LVM2.2.02.79.tgz
        tar xzvf LVM2.2.02.79.tgz
        cd LVM2.2.02.79
	export ac_cv_func_malloc_0_nonnull=yes
	CC=droid-gcc LD=droid-ld ./configure --host=arm-none-linux-gnueabi --enable-static --enable-lvm1_fallback --enable-fsadm --with-clvmd=cman --with-cluster=internal --with-pool=internal --with-user= --with-group= --with-dmdir=device-mapper.0 --with-usrlibdir=/usr/lib --with-usrsbindir=/usr/sbin --with-device-uid=0 --with-device-gid=6 --with-device-mode=0660 --enable-pkgconfig --with-static-link --with-clvmd=none --with-pool=none --with-cluster=none --with-snapshots=none --with-mirrors=none
	make
	cp lib/liblvm-internal.a $DROID_ROOT/out/target/product/generic/obj/lib 
	cp include/* $DROID_ROOT/out/target/product/generic/obj/include


//now POPT: http://freshmeat.net/projects/popt/
	mkdir $DROID_ROOT/external/popt
	cd $DROID_ROOT/external/popt
	wget http://freshmeat.net/urls/4917159c4dcafe43386a268ca8173744
	tar xzvf popt-1.14.tar.gz
	cd popt-1.14	
	./autogen.sh
	CC=droid-gcc LD=droid-ld ./configure --host=arm-none-linux-gnueabi --enable-static
	make
	cp .libs/libpopt.a $DROID_ROOT/out/target/product/generic/obj/lib
	cp popt.h $DROID_ROOT/out/target/product/generic/obj/include
	

//now (finally) we are ready to build cryptsetup LUKS

	//need to make sure you have uuid-dev
	sudo apt-get install uuid-dev zlib1g-dev

	mkdir $DROID_ROOT/external/cryptsetup
	cd $DROID_ROOT/external/cryptsetup

	wget http://cryptsetup.googlecode.com/files/cryptsetup-1.1.3.tar.bz2
	//and verify: wget http://cryptsetup.googlecode.com/files/cryptsetup-1.1.3.tar.bz2.asc 

	cd cryptsetup-1.1.3
	c_cv_func_malloc_0_nonnull=yes ac_cv_func_realloc_0_nonnull=yes CC=droid-gcc LD=droid-ld ./configure --host=arm-none-linux-gnueabi --build=arm-linux --enable-static

	
	
