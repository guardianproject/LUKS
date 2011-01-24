#!/bin/bash

	export DROID_ROOT=~/android/mydroid/

	export SOURCE_URL=http://ftp.linux.org.uk/pub/linux/arm/fedora/pub/fedora/linux/releases/11/Everything/arm/os/Packages/

        export FILE=e2fsprogs-devel-1.41.3-2.fc11.armv5tel.rpm
        wget $SOURCE_URL/$FILE
        mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
        cp root/usr/lib/* $DROID_ROOT/out/target/product/generic/obj/lib
        rm -rf root
        rm $FILE

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

        export FILE=libgcrypt-devel-1.4.4-4.fc11.armv5tel.rpm
        wget $SOURCE_URL/$FILE
        mkdir root && rpm2cpio $FILE | ( cd root && cpio -idv)
        cp root/usr/include/* $DROID_ROOT/out/target/product/generic/obj/include
        rm -rf root
        rm $FILE

