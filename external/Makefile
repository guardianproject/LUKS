# To build, install the Android NDK on GNU/Linux or Mac OS X:
#    http://developer.android.com/sdk/ndk/index.html

### these modify the calling shell
# point pkg-config to the .pc files generated from these builds
export PKG_CONFIG_PATH=$(LOCAL)/lib/pkgconfig
# workaround for cross-compiling bug in autoconf
export ac_cv_func_malloc_0_nonnull=yes

CWD = $(shell pwd)
PROJECT_ROOT = $(CWD)
EXTERNAL_ROOT = $(PROJECT_ROOT)/external

# Android NDK setup
NDK_BASE ?=  /usr/local/android-ndk
NDK_PLATFORM_LEVEL ?= 9
NDK_TOOLCHAIN_VERSION ?= 4.8
NDK_SYSROOT=$(NDK_BASE)/platforms/android-$(NDK_PLATFORM_LEVEL)/arch-arm
NDK_UNAME := $(shell uname -s | tr '[A-Z]' '[a-z]')
CPU := $(shell uname -m)
ifeq ($(CPU),x86_64)
  NDK_PROCESSOR=x86_64
else
  NDK_PROCESSOR=x86
endif
NDK_TOOLCHAIN=$(NDK_BASE)/toolchains/arm-linux-androideabi-$(NDK_TOOLCHAIN_VERSION)/prebuilt/$(NDK_UNAME)-$(NDK_PROCESSOR)

# to use the real HOST tag, you need the latest libtool files:
# http://stackoverflow.com/questions/4594736/configure-does-not-recognize-androideabi
HOST := arm-linux-androideabi

# install root for built files
DESTDIR = $(CWD)
# TODO try adding the Android-style /data/app.name here
prefix = 
LOCAL := $(DESTDIR)$(prefix)

PATH := ${PATH}:$(NDK_TOOLCHAIN)/bin:$(LOCAL)/bin

CC := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-gcc --sysroot=$(NDK_SYSROOT)
CXX := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-g++
CPP := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-cpp
LD := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-ld
RANLIB := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-ranlib
STRIP := $(NDK_TOOLCHAIN)/bin/arm-linux-androideabi-strip \
	--strip-unneeded -R .note -R .comment

CFLAGS = -I$(LOCAL)/include
LDFLAGS = -L$(LOCAL)/lib -Wl,--rpath,$(LOCAL)/lib
#ALL_LDFLAGS = -Wl,--entry=main,-rpath=$(ANDROID_NDK_ROOT)/build/platforms/android-$(NDK_PLATFORM_VER)/arch-arm/usr/lib,-dynamic-linker=/system/bin/linker -L$(NDK_SYSROOT)/usr/lib  -nostdlib -lc -ldl
#LIBS = -lc -ldl


#------------------------------------------------------------------------------#
# download sources and verify


SOURCEDIRS = libgpg-error-1.10 libgcrypt-1.4.6 popt-1.14 cryptsetup-1.2.0 LVM2.2.02.79 util-linux-ng-2.18

all: cryptsetup-install


#------------------------------------------------------------------------------#
# libgpg-error

libgpg-error-1.10.tar.bz2.sig: libgpg-error-1.10.tar.bz2
	wget ftp://ftp.gnupg.org/gcrypt/libgpg-error/libgpg-error-1.10.tar.bz2.sig
# TODO verify

libgpg-error-1.10.tar.bz2:
	wget -c ftp://ftp.gnupg.org/gcrypt/libgpg-error/libgpg-error-1.10.tar.bz2
	sha1sum -c libgpg-error-1.10.tar.bz2.sha1

libgpg-error-1.10: libgpg-error-1.10.tar.bz2.sig
	tar xjf libgpg-error-1.10.tar.bz2
	cp config.guess config.sub libgpg-error-1.10/

libgpg-error-1.10/config.sub: libgpg-error-1.10 config.sub
	cp config.sub libgpg-error-1.10/config.sub

libgpg-error-1.10/config.guess: libgpg-error-1.10 config.guess
	cp config.guess libgpg-error-1.10/config.guess

libgpg-error-build: libgpg-error-1.10/config.sub libgpg-error-1.10/config.guess
	cd libgpg-error-1.10 && \
		./configure CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			--disable-languages --enable-static --host=$(HOST)
	$(MAKE) -C libgpg-error-1.10

$(LOCAL)/lib/libgpg-error.a: libgpg-error-build
	$(MAKE) -C libgpg-error-1.10 DESTDIR=$(DESTDIR) prefix= install

libgpg-error-install: $(LOCAL)/lib/libgpg-error.a


#------------------------------------------------------------------------------#
# 

libgcrypt-1.4.6.tar.bz2.sig: libgcrypt-1.4.6.tar.bz2
	wget ftp://ftp.gnupg.org/gcrypt/libgcrypt/libgcrypt-1.4.6.tar.bz2.sig

libgcrypt-1.4.6.tar.bz2:
	wget -c ftp://ftp.gnupg.org/gcrypt/libgcrypt/libgcrypt-1.4.6.tar.bz2
	sha1sum -c libgcrypt-1.4.6.tar.bz2.sha1

libgcrypt-1.4.6: libgcrypt-1.4.6.tar.bz2.sig
	tar xjf libgcrypt-1.4.6.tar.bz2
	patch -p0 < libgcrypt-1.4.6-android.diff

libgcrypt-1.4.6/config.sub: libgcrypt-1.4.6 config.sub
	cp config.sub libgcrypt-1.4.6/config.sub

libgcrypt-1.4.6/config.guess: libgcrypt-1.4.6 config.guess
	cp config.guess libgcrypt-1.4.6/config.guess

libgcrypt-build: libgpg-error-install libgcrypt-1.4.6 libgcrypt-1.4.6/config.sub libgcrypt-1.4.6/config.guess
	cd libgcrypt-1.4.6 && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="-lgpg-error $(LDFLAGS)" \
			./configure --enable-maintainer-mode --enable-static --host=$(HOST) --with-gpg-error-prefix=$(LOCAL) --prefix=$(prefix)
	$(MAKE) -C libgcrypt-1.4.6

$(LOCAL)/lib/libgcrypt.a: libgcrypt-build
	$(MAKE) -C libgcrypt-1.4.6 DESTDIR=$(DESTDIR) prefix= install
	ls -l $(LOCAL)/lib/libgcrypt.a

libgcrypt-install: $(LOCAL)/lib/libgcrypt.a

#------------------------------------------------------------------------------#
# 

# TODObuild libuuid from e2fsprogs

#------------------------------------------------------------------------------#
# LVM2

LVM2.2.02.79.tgz.asc: LVM2.2.02.79.tgz
	wget ftp://sources.redhat.com/pub/lvm2/releases/LVM2.2.02.79.tgz.asc
# TODO verify

LVM2.2.02.79.tgz:
	wget -c ftp://sources.redhat.com/pub/lvm2/releases/LVM2.2.02.79.tgz
	sha1sum -c LVM2.2.02.79.tgz.sha1

LVM2.2.02.79: LVM2.2.02.79.tgz.asc
	tar xzf LVM2.2.02.79.tgz
	patch -p0 < LVM2.2.02.79-android.diff

LVM2.2.02.79/autoconf/config.sub: LVM2.2.02.79 config.sub
	cp config.sub LVM2.2.02.79/autoconf/config.sub

LVM2.2.02.79/autoconf/config.guess: LVM2.2.02.79 config.guess
	cp config.guess LVM2.2.02.79/autoconf/config.guess

#Now we have to get LVM2
lvm2-build: LVM2.2.02.79/autoconf/config.sub LVM2.2.02.79/autoconf/config.guess
	cd LVM2.2.02.79 && \
		autoreconf && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" ./configure --host=$(HOST) --prefix=$(prefix) --enable-static_link --enable-lvm1_fallback --enable-fsadm --with-clvmd=cman --with-cluster=internal --with-pool=internal --with-user= --with-group= --with-dmdir=device-mapper.0 --with-device-uid=0 --with-device-gid=6 --with-device-mode=0660 --with-clvmd=none --with-pool=none --with-cluster=none --with-snapshots=none --with-mirrors=none --enable-pkgconfig
	$(MAKE) CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" -C LVM2.2.02.79

$(LOCAL)/lib/libdevmapper.a: lvm2-build
	$(MAKE) -C LVM2.2.02.79 DESTDIR=$(DESTDIR) prefix= install

lvm2-install: $(LOCAL)/lib/libdevmapper.a


#------------------------------------------------------------------------------#
# popt

popt-1.16.tar.gz.asc: popt-1.16.tar.gz
	touch $@
# TODO verify

popt-1.16.tar.gz:
	wget -c http://rpm5.org/files/popt/popt-1.16.tar.gz
	#sha1sum -c popt-1.16.tar.gz.sha1

popt-1.16: popt-1.16.tar.gz.asc
	tar xzf popt-1.16.tar.gz
	patch -p0 < popt-1.16-android.diff

popt-1.16/config.sub: popt-1.16 config.sub
	cp config.sub popt-1.16/config.sub

popt-1.16/config.guess: popt-1.16 config.guess
	cp config.guess popt-1.16/config.guess

popt-build: popt-1.16 popt-1.16/config.sub popt-1.16/config.guess
	cd popt-1.16 && \
		autoreconf -i && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			./configure --prefix=$(prefix) --host=$(HOST) --enable-static
	$(MAKE) -C popt-1.16

$(LOCAL)/lib/libpopt.a: popt-build
	$(MAKE) -C popt-1.16 DESTDIR=$(DESTDIR) prefix= install

popt-install: $(LOCAL)/lib/libpopt.a


#------------------------------------------------------------------------------#
# util-linux

util-linux-ng-2.18.tar.sign: util-linux-ng-2.18.tar.bz2
	wget http://www.kernel.org/pub/linux/utils/util-linux/v2.18/util-linux-ng-2.18.tar.sign

util-linux-ng-2.18.tar.bz2:
	wget -c http://www.kernel.org/pub/linux/utils/util-linux/v2.18/util-linux-ng-2.18.tar.bz2
	sha1sum -c util-linux-ng-2.18.tar.bz2.sha1

util-linux-ng-2.18: util-linux-ng-2.18.tar.sign
	tar xjf util-linux-ng-2.18.tar.bz2
	patch -p0 < util-linux-ng-2.18-android.diff

util-linux-ng-2.18/autoconf/config.sub: util-linux-ng-2.18 config.sub
	cp config.sub util-linux-ng-2.18/config/config.sub

util-linux-ng-2.18/autoconf/config.guess: util-linux-ng-2.18 config.guess
	cp config.guess util-linux-ng-2.18/config/config.guess

util-linux-ng-build: util-linux-ng-2.18/autoconf/config.guess util-linux-ng-2.18/autoconf/config.sub
	cd util-linux-ng-2.18 && \
		./autogen.sh && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			./configure --host=$(HOST) --prefix=$(prefix) --enable-static --without-ncurses --disable-unshare --disable-rename --disable-wall --disable-mount --disable-fsck --disable-libblkid --disable-libmount --enable-pkgconfig
	$(MAKE) -C util-linux-ng-2.18

$(LOCAL)/lib/libuuid.a: util-linux-ng-build
	$(MAKE) -C util-linux-ng-2.18 DESTDIR=$(DESTDIR) prefix= install

util-linux-ng-install: $(LOCAL)/lib/libuuid.a


#------------------------------------------------------------------------------#
# cryptsetup

cryptsetup-1.2.0.tar.bz2.asc: cryptsetup-1.2.0.tar.bz2
	wget http://cryptsetup.googlecode.com/files/cryptsetup-1.2.0.tar.bz2.asc
# TODO verify

cryptsetup-1.2.0.tar.bz2: 
	wget -c http://cryptsetup.googlecode.com/files/cryptsetup-1.2.0.tar.bz2
	sha1sum -c cryptsetup-1.2.0.tar.bz2.sha1

cryptsetup-1.2.0: cryptsetup-1.2.0.tar.bz2.asc
	tar xjf cryptsetup-1.2.0.tar.bz2
	patch -p0 < cryptsetup-1.2.0-android.diff

cryptsetup-1.2.0/config.sub: cryptsetup-1.2.0 config.sub
	cp config.sub cryptsetup-1.2.0/config.sub

cryptsetup-1.2.0/config.guess: cryptsetup-1.2.0 config.guess
	cp config.guess cryptsetup-1.2.0/config.guess

cryptsetup-build: $(LOCAL)/lib/libgpg-error.a $(LOCAL)/lib/libgcrypt.a $(LOCAL)/lib/libdevmapper.a $(LOCAL)/lib/libpopt.a $(LOCAL)/lib/libuuid.a cryptsetup-1.2.0 cryptsetup-1.2.0/config.sub cryptsetup-1.2.0/config.guess
	rm lib/*.la
	cd cryptsetup-1.2.0 && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			./configure --host=$(HOST) --prefix=$(prefix) --enable-static --enable-libgcrypt --enable-libdevmapper --disable-selinux --enable-static-cryptsetup
	$(MAKE) -C cryptsetup-1.2.0

$(LOCAL)/sbin/cryptsetup: cryptsetup-build
	$(MAKE) -C cryptsetup-1.2.0 DESTDIR=$(DESTDIR) prefix= install

cryptsetup-install: $(LOCAL)/sbin/cryptsetup

#------------------------------------------------------------------------------#
# clean

tarballs_clean:
	rm -f -- rm *.sig *.bz2 *.gz *.tgz *.asc

install_clean:
	rm -rf -- bin include lib sbin share

sourcedirs_clean:
	rm -rf -- $(SOURCEDIRS)

clean: sourcedirs_clean install_clean

distclean: clean tarballs_clean

.PHONY = libgpg-error-build libgcrypt-build lvm2-build popt-build	\
	cryptosetup-build util-linux-ng-build libgpg-error-install		\
	libgcrypt-install lvm2-install popt-install cryptosetup-install	\
	util-linux-ng-install clean tarballs_clean sourcedirs_clean
