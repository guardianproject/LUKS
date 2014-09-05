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


SOURCEDIRS = libgpg-error libgcrypt popt-1.16 cryptsetup lvm2 util-linux-ng-2.18

all: cryptsetup-install


#------------------------------------------------------------------------------#
# libgpg-error

libgpg-error/config.sub: config.sub
	cp config.sub libgpg-error/config.sub

libgpg-error/config.guess: config.guess
	cp config.guess libgpg-error/config.guess

libgpg-error/Makefile: libgpg-error/config.sub libgpg-error/config.guess
	cd libgpg-error && \
		./autogen.sh && automake --add-missing && \
		./configure CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			--disable-languages --enable-static --host=$(HOST)

libgpg-error/src/.libs/libgpg-error.a: libgpg-error/Makefile
	$(MAKE) -C libgpg-error

libgpg-error-build: libgpg-error/src/.libs/libgpg-error.a

$(LOCAL)/lib/libgpg-error.a: libgpg-error-build
	$(MAKE) -C libgpg-error DESTDIR=$(DESTDIR) prefix= install

libgpg-error-install: $(LOCAL)/lib/libgpg-error.a


#------------------------------------------------------------------------------#
# 

libgcrypt/config.sub: config.sub
	cp config.sub libgcrypt/config.sub

libgcrypt/config.guess: config.guess
	cp config.guess libgcrypt/config.guess

libgcrypt/Makefile: libgcrypt/config.sub libgcrypt/config.guess
	cd libgcrypt && \
		autoreconf -i && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="-lgpg-error $(LDFLAGS)" \
			./configure --enable-maintainer-mode --enable-static --host=$(HOST) --with-gpg-error-prefix=$(LOCAL) --prefix=$(prefix)

libgcrypt/src/.libs/libgcrypt.a: $(LOCAL)/lib/libgpg-error.a libgcrypt/Makefile
	$(MAKE) -C libgcrypt

libgcrypt-build: libgcrypt/src/.libs/libgcrypt.a

$(LOCAL)/lib/libgcrypt.a: libgcrypt-build
	$(MAKE) -C libgcrypt DESTDIR=$(DESTDIR) prefix= install

libgcrypt-install: $(LOCAL)/lib/libgcrypt.a

#------------------------------------------------------------------------------#
# 

# TODObuild libuuid from e2fsprogs

#------------------------------------------------------------------------------#
# LVM2

lvm2/Makefile:
	cp config.sub lvm2/autoconf/config.sub
	cp config.guess lvm2/autoconf/config.guess
	-patch -N -p0 --reject-file=- < lvm2-android.diff
	cd lvm2 && \
		autoreconf && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" ./configure --host=$(HOST) --prefix=$(prefix) --enable-static_link --enable-lvm1_fallback --enable-fsadm --with-clvmd=cman --with-cluster=internal --with-pool=internal --with-user= --with-group= --with-dmdir=device-mapper.0 --with-device-uid=0 --with-device-gid=6 --with-device-mode=0660 --with-clvmd=none --with-pool=none --with-cluster=none --with-snapshots=none --with-mirrors=none --enable-pkgconfig

lvm2/libdm/ioctl/libdevmapper.a: lvm2/Makefile
	$(MAKE) CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" -C lvm2

lvm2-build: lvm2/libdm/ioctl/libdevmapper.a

$(LOCAL)/lib/libdevmapper.a: lvm2-build
	$(MAKE) -C lvm2 DESTDIR=$(DESTDIR) prefix= install

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

cryptsetup/config.sub: config.sub
	cp config.sub cryptsetup/config.sub

cryptsetup/config.guess: config.guess
	cp config.guess cryptsetup/config.guess

cryptsetup/Makefile: cryptsetup/config.sub cryptsetup/config.guess
	-patch -N -p0 --reject-file=- < cryptsetup-android.diff
	rm -f lib/*.la
	cd cryptsetup && \
	  autoreconf -i && \
		CC="$(CC)" CFLAGS="$(CFLAGS)" LDFLAGS="$(LDFLAGS)" \
			./configure --host=$(HOST) --prefix=$(prefix) --enable-static --enable-libgcrypt --enable-libdevmapper --disable-selinux --enable-static-cryptsetup --disable-kernel_crypto --enable-reencrypt

cryptsetup/src/cryptsetup.static: $(LOCAL)/lib/libgpg-error.a $(LOCAL)/lib/libgcrypt.a $(LOCAL)/lib/libdevmapper.a $(LOCAL)/lib/libpopt.a $(LOCAL)/lib/libuuid.a cryptsetup/Makefile
	$(MAKE) -C cryptsetup

cryptsetup-build: cryptsetup/src/cryptsetup.static

$(LOCAL)/sbin/cryptsetup: cryptsetup-build
	$(MAKE) -C cryptsetup DESTDIR=$(DESTDIR) prefix= install

cryptsetup-install: $(LOCAL)/sbin/cryptsetup

cryptsetup-clean:
	-$(MAKE) -C cryptsetup clean

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
