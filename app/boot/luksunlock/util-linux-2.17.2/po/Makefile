# Makefile for PO directory in any package using GNU gettext.
# Copyright (C) 1995-1997, 2000-2004 by Ulrich Drepper <drepper@gnu.ai.mit.edu>
#
# This file can be copied and used freely without restrictions.  It can
# be used in projects which are not available under the GNU General Public
# License but which still want to provide support for the GNU gettext
# functionality.
# Please note that the actual code of GNU gettext is covered by the GNU
# General Public License and is *not* in the public domain.
#
# Origin: gettext-0.14

PACKAGE = util-linux-ng
VERSION = 2.17.2

SHELL = /bin/sh


srcdir = .
top_srcdir = ..


prefix = /usr
exec_prefix = ${prefix}
datadir = ${prefix}/share
localedir = $(datadir)/locale
gettextsrcdir = $(datadir)/gettext/po

INSTALL = /usr/bin/install -c
INSTALL_DATA = ${INSTALL} -m 644
MKINSTALLDIRS = $(top_builddir)/config/mkinstalldirs
mkinstalldirs = $(SHELL) $(MKINSTALLDIRS)

GMSGFMT = /usr/bin/msgfmt
MSGFMT = /usr/bin/msgfmt
XGETTEXT = /usr/bin/xgettext
MSGMERGE = msgmerge
MSGMERGE_UPDATE = /usr/bin/msgmerge --update
MSGINIT = msginit
MSGCONV = msgconv
MSGFILTER = msgfilter

POFILES =  ca.po cs.po da.po de.po es.po et.po eu.po fi.po fr.po hu.po id.po it.po ja.po nl.po pl.po pt_BR.po ru.po sl.po sv.po tr.po uk.po vi.po zh_CN.po
GMOFILES =  ca.gmo cs.gmo da.gmo de.gmo es.gmo et.gmo eu.gmo fi.gmo fr.gmo hu.gmo id.gmo it.gmo ja.gmo nl.gmo pl.gmo pt_BR.gmo ru.gmo sl.gmo sv.gmo tr.gmo uk.gmo vi.gmo zh_CN.gmo
UPDATEPOFILES =  ca.po-update cs.po-update da.po-update de.po-update es.po-update et.po-update eu.po-update fi.po-update fr.po-update hu.po-update id.po-update it.po-update ja.po-update nl.po-update pl.po-update pt_BR.po-update ru.po-update sl.po-update sv.po-update tr.po-update uk.po-update vi.po-update zh_CN.po-update
DUMMYPOFILES =  ca.nop cs.nop da.nop de.nop es.nop et.nop eu.nop fi.nop fr.nop hu.nop id.nop it.nop ja.nop nl.nop pl.nop pt_BR.nop ru.nop sl.nop sv.nop tr.nop uk.nop vi.nop zh_CN.nop
DISTFILES.common = Makefile.in.in remove-potcdate.sin \
$(DISTFILES.common.extra1) $(DISTFILES.common.extra2) $(DISTFILES.common.extra3)
DISTFILES = $(DISTFILES.common) Makevars POTFILES.in $(DOMAIN).pot stamp-po \
$(POFILES) $(GMOFILES) \
$(DISTFILES.extra1) $(DISTFILES.extra2) $(DISTFILES.extra3)

POTFILES = \
     ../disk-utils/blockdev.c \
     ../disk-utils/cramfs_common.c \
     ../disk-utils/elvtune.c \
     ../disk-utils/fdformat.c \
     ../disk-utils/fsck.cramfs.c \
     ../disk-utils/fsck.minix.c \
     ../disk-utils/isosize.c \
     ../disk-utils/mkfs.bfs.c \
     ../disk-utils/mkfs.c \
     ../disk-utils/mkfs.cramfs.c \
     ../disk-utils/mkfs.minix.c \
     ../disk-utils/mkswap.c \
     ../disk-utils/raw.c \
     ../fdisk/cfdisk.c \
     ../fdisk/fdiskaixlabel.c \
     ../fdisk/fdiskbsdlabel.c \
     ../fdisk/fdisk.c \
     ../fdisk/fdiskmaclabel.c \
     ../fdisk/fdisksgilabel.c \
     ../fdisk/fdisksunlabel.c \
     ../fdisk/gpt.c \
     ../fdisk/i386_sys_types.c \
     ../fdisk/partitiontype.c \
     ../fdisk/partname.c \
     ../fdisk/sfdisk.c \
     ../fsck/base_device.c \
     ../fsck/fsck.c \
     ../getopt/getopt.c \
     ../hwclock/clock-ppc.c \
     ../hwclock/cmos.c \
     ../hwclock/hwclock.c \
     ../hwclock/kd.c \
     ../hwclock/rtc.c \
     ../lib/blkdev.c \
     ../lib/canonicalize.c \
     ../lib/crc32.c \
     ../lib/env.c \
     ../lib/fsprobe.c \
     ../lib/ismounted.c \
     ../lib/linux_version.c \
     ../lib/md5.c \
     ../lib/setproctitle.c \
     ../lib/wholedisk.c \
     ../login-utils/agetty.c \
     ../login-utils/checktty.c \
     ../login-utils/chfn.c \
     ../login-utils/chsh.c \
     ../login-utils/initctl.c \
     ../login-utils/islocal.c \
     ../login-utils/last.c \
     ../login-utils/login.c \
     ../login-utils/mesg.c \
     ../login-utils/newgrp.c \
     ../login-utils/selinux_utils.c \
     ../login-utils/setpwnam.c \
     ../login-utils/shutdown.c \
     ../login-utils/simpleinit.c \
     ../login-utils/ttymsg.c \
     ../login-utils/vipw.c \
     ../login-utils/wall.c \
     ../misc-utils/blkid.c \
     ../misc-utils/cal.c \
     ../misc-utils/ddate.c \
     ../misc-utils/findfs.c \
     ../misc-utils/kill.c \
     ../misc-utils/logger.c \
     ../misc-utils/look.c \
     ../misc-utils/mcookie.c \
     ../misc-utils/namei.c \
     ../misc-utils/procs.c \
     ../misc-utils/rename.c \
     ../misc-utils/script.c \
     ../misc-utils/scriptreplay.c \
     ../misc-utils/setterm.c \
     ../misc-utils/uuidd.c \
     ../misc-utils/uuidgen.c \
     ../misc-utils/whereis.c \
     ../misc-utils/wipefs.c \
     ../misc-utils/write.c \
     ../mount/devname.c \
     ../mount/fstab.c \
     ../mount/getusername.c \
     ../mount/lomount.c \
     ../mount/mount.c \
     ../mount/mount_mntent.c \
     ../mount/sundries.c \
     ../mount/swapon.c \
     ../mount/umount.c \
     ../mount/xmalloc.c \
     ../partx/addpart.c \
     ../partx/bsd.c \
     ../partx/crc32.c \
     ../partx/delpart.c \
     ../partx/dos.c \
     ../partx/gpt.c \
     ../partx/partx.c \
     ../partx/solaris.c \
     ../partx/unixware.c \
     ../schedutils/chrt.c \
     ../schedutils/ionice.c \
     ../schedutils/taskset.c \
     ../shlibs/blkid/samples/mkfs.c \
     ../shlibs/blkid/samples/partitions.c \
     ../shlibs/blkid/samples/superblocks.c \
     ../shlibs/blkid/samples/topology.c \
     ../shlibs/blkid/src/cache.c \
     ../shlibs/blkid/src/config.c \
     ../shlibs/blkid/src/dev.c \
     ../shlibs/blkid/src/devname.c \
     ../shlibs/blkid/src/devno.c \
     ../shlibs/blkid/src/encode.c \
     ../shlibs/blkid/src/evaluate.c \
     ../shlibs/blkid/src/getsize.c \
     ../shlibs/blkid/src/llseek.c \
     ../shlibs/blkid/src/partitions/aix.c \
     ../shlibs/blkid/src/partitions/bsd.c \
     ../shlibs/blkid/src/partitions/dos.c \
     ../shlibs/blkid/src/partitions/gpt.c \
     ../shlibs/blkid/src/partitions/mac.c \
     ../shlibs/blkid/src/partitions/minix.c \
     ../shlibs/blkid/src/partitions/partitions.c \
     ../shlibs/blkid/src/partitions/sgi.c \
     ../shlibs/blkid/src/partitions/solaris_x86.c \
     ../shlibs/blkid/src/partitions/sun.c \
     ../shlibs/blkid/src/partitions/unixware.c \
     ../shlibs/blkid/src/probe.c \
     ../shlibs/blkid/src/read.c \
     ../shlibs/blkid/src/resolve.c \
     ../shlibs/blkid/src/save.c \
     ../shlibs/blkid/src/superblocks/adaptec_raid.c \
     ../shlibs/blkid/src/superblocks/bfs.c \
     ../shlibs/blkid/src/superblocks/btrfs.c \
     ../shlibs/blkid/src/superblocks/cramfs.c \
     ../shlibs/blkid/src/superblocks/ddf_raid.c \
     ../shlibs/blkid/src/superblocks/ext.c \
     ../shlibs/blkid/src/superblocks/gfs.c \
     ../shlibs/blkid/src/superblocks/hfs.c \
     ../shlibs/blkid/src/superblocks/highpoint_raid.c \
     ../shlibs/blkid/src/superblocks/hpfs.c \
     ../shlibs/blkid/src/superblocks/iso9660.c \
     ../shlibs/blkid/src/superblocks/isw_raid.c \
     ../shlibs/blkid/src/superblocks/jfs.c \
     ../shlibs/blkid/src/superblocks/jmicron_raid.c \
     ../shlibs/blkid/src/superblocks/linux_raid.c \
     ../shlibs/blkid/src/superblocks/lsi_raid.c \
     ../shlibs/blkid/src/superblocks/luks.c \
     ../shlibs/blkid/src/superblocks/lvm.c \
     ../shlibs/blkid/src/superblocks/minix.c \
     ../shlibs/blkid/src/superblocks/netware.c \
     ../shlibs/blkid/src/superblocks/ntfs.c \
     ../shlibs/blkid/src/superblocks/nvidia_raid.c \
     ../shlibs/blkid/src/superblocks/ocfs.c \
     ../shlibs/blkid/src/superblocks/promise_raid.c \
     ../shlibs/blkid/src/superblocks/reiserfs.c \
     ../shlibs/blkid/src/superblocks/romfs.c \
     ../shlibs/blkid/src/superblocks/silicon_raid.c \
     ../shlibs/blkid/src/superblocks/squashfs.c \
     ../shlibs/blkid/src/superblocks/superblocks.c \
     ../shlibs/blkid/src/superblocks/swap.c \
     ../shlibs/blkid/src/superblocks/sysv.c \
     ../shlibs/blkid/src/superblocks/ubifs.c \
     ../shlibs/blkid/src/superblocks/udf.c \
     ../shlibs/blkid/src/superblocks/ufs.c \
     ../shlibs/blkid/src/superblocks/vfat.c \
     ../shlibs/blkid/src/superblocks/via_raid.c \
     ../shlibs/blkid/src/superblocks/vmfs.c \
     ../shlibs/blkid/src/superblocks/vxfs.c \
     ../shlibs/blkid/src/superblocks/xfs.c \
     ../shlibs/blkid/src/superblocks/zfs.c \
     ../shlibs/blkid/src/tag.c \
     ../shlibs/blkid/src/topology/dm.c \
     ../shlibs/blkid/src/topology/evms.c \
     ../shlibs/blkid/src/topology/ioctl.c \
     ../shlibs/blkid/src/topology/lvm.c \
     ../shlibs/blkid/src/topology/md.c \
     ../shlibs/blkid/src/topology/sysfs.c \
     ../shlibs/blkid/src/topology/topology.c \
     ../shlibs/blkid/src/tst_types.c \
     ../shlibs/blkid/src/verify.c \
     ../shlibs/blkid/src/version.c \
     ../shlibs/uuid/src/clear.c \
     ../shlibs/uuid/src/compare.c \
     ../shlibs/uuid/src/copy.c \
     ../shlibs/uuid/src/gen_uuid.c \
     ../shlibs/uuid/src/gen_uuid_nt.c \
     ../shlibs/uuid/src/isnull.c \
     ../shlibs/uuid/src/pack.c \
     ../shlibs/uuid/src/parse.c \
     ../shlibs/uuid/src/tst_uuid.c \
     ../shlibs/uuid/src/unpack.c \
     ../shlibs/uuid/src/unparse.c \
     ../shlibs/uuid/src/uuid_time.c \
     ../sys-utils/arch.c \
     ../sys-utils/ctrlaltdel.c \
     ../sys-utils/cytune.c \
     ../sys-utils/dmesg.c \
     ../sys-utils/fallocate.c \
     ../sys-utils/flock.c \
     ../sys-utils/ipcmk.c \
     ../sys-utils/ipcrm.c \
     ../sys-utils/ipcs.c \
     ../sys-utils/ldattach.c \
     ../sys-utils/lscpu.c \
     ../sys-utils/pivot_root.c \
     ../sys-utils/rdev.c \
     ../sys-utils/readprofile.c \
     ../sys-utils/renice.c \
     ../sys-utils/rtcwake.c \
     ../sys-utils/setarch.c \
     ../sys-utils/setsid.c \
     ../sys-utils/switch_root.c \
     ../sys-utils/tunelp.c \
     ../sys-utils/unshare.c \
     ../text-utils/col.c \
     ../text-utils/colcrt.c \
     ../text-utils/colrm.c \
     ../text-utils/column.c \
     ../text-utils/conv.c \
     ../text-utils/display.c \
     ../text-utils/hexdump.c \
     ../text-utils/hexsyntax.c \
     ../text-utils/line.c \
     ../text-utils/more.c \
     ../text-utils/odsyntax.c \
     ../text-utils/parse.c \
     ../text-utils/pg.c \
     ../text-utils/rev.c \
     ../text-utils/tailf.c \
     ../text-utils/ul.c

CATALOGS =  ca.gmo cs.gmo da.gmo de.gmo es.gmo et.gmo eu.gmo fi.gmo fr.gmo hu.gmo id.gmo it.gmo ja.gmo nl.gmo pl.gmo pt_BR.gmo ru.gmo sl.gmo sv.gmo tr.gmo uk.gmo vi.gmo zh_CN.gmo

# Makevars gets inserted here. (Don't remove this line!)
# Makefile variables for PO directory in any package using GNU gettext.

# Usually the message domain is the same as the package name.
DOMAIN = $(PACKAGE)

# These two variables depend on the location of this directory.
subdir = po
top_builddir = ..

# These options get passed to xgettext.
XGETTEXT_OPTIONS = --keyword=_ --keyword=N_

# This is the copyright holder that gets inserted into the header of the
# $(DOMAIN).pot file.  Set this to the copyright holder of the surrounding
# package.  (Note that the msgstr strings, extracted from the package's
# sources, belong to the copyright holder of the package.)  Translators are
# expected to transfer the copyright for their translations to this person
# or entity, or to disclaim their copyright.  The empty string stands for
# the public domain; in this case the translators are expected to disclaim
# their copyright.
COPYRIGHT_HOLDER = Karel Zak <kzak@redhat.com>

# This is the email address or URL to which the translators shall report
# bugs in the untranslated strings:
# - Strings which are not entire sentences, see the maintainer guidelines
#   in the GNU gettext documentation, section 'Preparing Strings'.
# - Strings which use unclear terms or require additional context to be
#   understood.
# - Strings which make invalid assumptions about notation of date, time or
#   money.
# - Pluralisation problems.
# - Incorrect English spelling.
# - Incorrect formatting.
# It can be your email address, or a mailing list address where translators
# can write to without being subscribed, or the URL of a web page through
# which the translators can contact you.
MSGID_BUGS_ADDRESS = Karel Zak <kzak@redhat.com>

# This is the list of locale categories, beyond LC_MESSAGES, for which the
# message catalogs shall be used.  It is usually empty.
EXTRA_LOCALE_CATEGORIES =

.SUFFIXES:
.SUFFIXES: .po .gmo .mo .sed .sin .nop .po-create .po-update

.po.mo:
	@echo "$(MSGFMT) -c -o $@ $<"; \
	$(MSGFMT) -c -o t-$@ $< && mv t-$@ $@

.po.gmo:
	@lang=`echo $* | sed -e 's,.*/,,'`; \
	test "$(srcdir)" = . && cdcmd="" || cdcmd="cd $(srcdir) && "; \
	echo "$${cdcmd}rm -f $${lang}.gmo && $(GMSGFMT) -c --statistics -o $${lang}.gmo $${lang}.po"; \
	cd $(srcdir) && rm -f $${lang}.gmo && $(GMSGFMT) -c --statistics -o t-$${lang}.gmo $${lang}.po && mv t-$${lang}.gmo $${lang}.gmo

.sin.sed:
	sed -e '/^#/d' $< > t-$@
	mv t-$@ $@


all: all-no

all-yes: stamp-po
all-no:

# stamp-po is a timestamp denoting the last time at which the CATALOGS have
# been loosely updated. Its purpose is that when a developer or translator
# checks out the package via CVS, and the $(DOMAIN).pot file is not in CVS,
# "make" will update the $(DOMAIN).pot and the $(CATALOGS), but subsequent
# invocations of "make" will do nothing. This timestamp would not be necessary
# if updating the $(CATALOGS) would always touch them; however, the rule for
# $(POFILES) has been designed to not touch files that don't need to be
# changed.
stamp-po: $(srcdir)/$(DOMAIN).pot
	test -z "$(GMOFILES)" || $(MAKE) $(GMOFILES)
	@echo "touch stamp-po"
	@echo timestamp > stamp-poT
	@mv stamp-poT stamp-po

# Note: Target 'all' must not depend on target '$(DOMAIN).pot-update',
# otherwise packages like GCC can not be built if only parts of the source
# have been downloaded.

# This target rebuilds $(DOMAIN).pot; it is an expensive operation.
# Note that $(DOMAIN).pot is not touched if it doesn't need to be changed.
$(DOMAIN).pot-update: $(POTFILES) $(srcdir)/POTFILES.in remove-potcdate.sed
	$(XGETTEXT) --default-domain=$(DOMAIN) --directory=$(top_srcdir) \
	  --add-comments=TRANSLATORS: $(XGETTEXT_OPTIONS) \
	  --files-from=$(srcdir)/POTFILES.in \
	  --copyright-holder='$(COPYRIGHT_HOLDER)' \
	  --msgid-bugs-address='$(MSGID_BUGS_ADDRESS)'
	test ! -f $(DOMAIN).po || { \
	  if test -f $(srcdir)/$(DOMAIN).pot; then \
	    sed -f remove-potcdate.sed < $(srcdir)/$(DOMAIN).pot > $(DOMAIN).1po && \
	    sed -f remove-potcdate.sed < $(DOMAIN).po > $(DOMAIN).2po && \
	    if cmp $(DOMAIN).1po $(DOMAIN).2po >/dev/null 2>&1; then \
	      rm -f $(DOMAIN).1po $(DOMAIN).2po $(DOMAIN).po; \
	    else \
	      rm -f $(DOMAIN).1po $(DOMAIN).2po $(srcdir)/$(DOMAIN).pot && \
	      mv $(DOMAIN).po $(srcdir)/$(DOMAIN).pot; \
	    fi; \
	  else \
	    mv $(DOMAIN).po $(srcdir)/$(DOMAIN).pot; \
	  fi; \
	}

# This rule has no dependencies: we don't need to update $(DOMAIN).pot at
# every "make" invocation, only create it when it is missing.
# Only "make $(DOMAIN).pot-update" or "make dist" will force an update.
$(srcdir)/$(DOMAIN).pot:
	$(MAKE) $(DOMAIN).pot-update

# This target rebuilds a PO file if $(DOMAIN).pot has changed.
# Note that a PO file is not touched if it doesn't need to be changed.
$(POFILES): $(srcdir)/$(DOMAIN).pot
	@lang=`echo $@ | sed -e 's,.*/,,' -e 's/\.po$$//'`; \
	if test -f "$(srcdir)/$${lang}.po"; then \
	  test "$(srcdir)" = . && cdcmd="" || cdcmd="cd $(srcdir) && "; \
	  echo "$${cdcmd}$(MSGMERGE_UPDATE) $${lang}.po $(DOMAIN).pot"; \
	  cd $(srcdir) && $(MSGMERGE_UPDATE) $${lang}.po $(DOMAIN).pot; \
	else \
	  $(MAKE) $${lang}.po-create; \
	fi


install: install-exec install-data
install-exec:
install-data: install-data-no
	if test "$(PACKAGE)" = "gettext-tools"; then \
	  $(mkinstalldirs) $(DESTDIR)$(gettextsrcdir); \
	  for file in $(DISTFILES.common) Makevars.template; do \
	    $(INSTALL_DATA) $(srcdir)/$$file \
			    $(DESTDIR)$(gettextsrcdir)/$$file; \
	  done; \
	  for file in Makevars; do \
	    rm -f $(DESTDIR)$(gettextsrcdir)/$$file; \
	  done; \
	else \
	  : ; \
	fi
install-data-no: all
install-data-yes: all
	$(mkinstalldirs) $(DESTDIR)$(datadir)
	@catalogs='$(CATALOGS)'; \
	for cat in $$catalogs; do \
	  cat=`basename $$cat`; \
	  lang=`echo $$cat | sed -e 's/\.gmo$$//'`; \
	  dir=$(localedir)/$$lang/LC_MESSAGES; \
	  $(mkinstalldirs) $(DESTDIR)$$dir; \
	  if test -r $$cat; then realcat=$$cat; else realcat=$(srcdir)/$$cat; fi; \
	  $(INSTALL_DATA) $$realcat $(DESTDIR)$$dir/$(DOMAIN).mo; \
	  echo "installing $$realcat as $(DESTDIR)$$dir/$(DOMAIN).mo"; \
	  for lc in '' $(EXTRA_LOCALE_CATEGORIES); do \
	    if test -n "$$lc"; then \
	      if (cd $(DESTDIR)$(localedir)/$$lang && LC_ALL=C ls -l -d $$lc 2>/dev/null) | grep ' -> ' >/dev/null; then \
	        link=`cd $(DESTDIR)$(localedir)/$$lang && LC_ALL=C ls -l -d $$lc | sed -e 's/^.* -> //'`; \
	        mv $(DESTDIR)$(localedir)/$$lang/$$lc $(DESTDIR)$(localedir)/$$lang/$$lc.old; \
	        mkdir $(DESTDIR)$(localedir)/$$lang/$$lc; \
	        (cd $(DESTDIR)$(localedir)/$$lang/$$lc.old && \
	         for file in *; do \
	           if test -f $$file; then \
	             ln -s ../$$link/$$file $(DESTDIR)$(localedir)/$$lang/$$lc/$$file; \
	           fi; \
	         done); \
	        rm -f $(DESTDIR)$(localedir)/$$lang/$$lc.old; \
	      else \
	        if test -d $(DESTDIR)$(localedir)/$$lang/$$lc; then \
	          :; \
	        else \
	          rm -f $(DESTDIR)$(localedir)/$$lang/$$lc; \
	          mkdir $(DESTDIR)$(localedir)/$$lang/$$lc; \
	        fi; \
	      fi; \
	      rm -f $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo; \
	      ln -s ../LC_MESSAGES/$(DOMAIN).mo $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo 2>/dev/null || \
	      ln $(DESTDIR)$(localedir)/$$lang/LC_MESSAGES/$(DOMAIN).mo $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo 2>/dev/null || \
	      cp -p $(DESTDIR)$(localedir)/$$lang/LC_MESSAGES/$(DOMAIN).mo $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo; \
	      echo "installing $$realcat link as $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo"; \
	    fi; \
	  done; \
	done

install-strip: install

installdirs: installdirs-exec installdirs-data
installdirs-exec:
installdirs-data: installdirs-data-no
	if test "$(PACKAGE)" = "gettext-tools"; then \
	  $(mkinstalldirs) $(DESTDIR)$(gettextsrcdir); \
	else \
	  : ; \
	fi
installdirs-data-no:
installdirs-data-yes:
	$(mkinstalldirs) $(DESTDIR)$(datadir)
	@catalogs='$(CATALOGS)'; \
	for cat in $$catalogs; do \
	  cat=`basename $$cat`; \
	  lang=`echo $$cat | sed -e 's/\.gmo$$//'`; \
	  dir=$(localedir)/$$lang/LC_MESSAGES; \
	  $(mkinstalldirs) $(DESTDIR)$$dir; \
	  for lc in '' $(EXTRA_LOCALE_CATEGORIES); do \
	    if test -n "$$lc"; then \
	      if (cd $(DESTDIR)$(localedir)/$$lang && LC_ALL=C ls -l -d $$lc 2>/dev/null) | grep ' -> ' >/dev/null; then \
	        link=`cd $(DESTDIR)$(localedir)/$$lang && LC_ALL=C ls -l -d $$lc | sed -e 's/^.* -> //'`; \
	        mv $(DESTDIR)$(localedir)/$$lang/$$lc $(DESTDIR)$(localedir)/$$lang/$$lc.old; \
	        mkdir $(DESTDIR)$(localedir)/$$lang/$$lc; \
	        (cd $(DESTDIR)$(localedir)/$$lang/$$lc.old && \
	         for file in *; do \
	           if test -f $$file; then \
	             ln -s ../$$link/$$file $(DESTDIR)$(localedir)/$$lang/$$lc/$$file; \
	           fi; \
	         done); \
	        rm -f $(DESTDIR)$(localedir)/$$lang/$$lc.old; \
	      else \
	        if test -d $(DESTDIR)$(localedir)/$$lang/$$lc; then \
	          :; \
	        else \
	          rm -f $(DESTDIR)$(localedir)/$$lang/$$lc; \
	          mkdir $(DESTDIR)$(localedir)/$$lang/$$lc; \
	        fi; \
	      fi; \
	    fi; \
	  done; \
	done

# Define this as empty until I found a useful application.
installcheck:

uninstall: uninstall-exec uninstall-data
uninstall-exec:
uninstall-data: uninstall-data-no
	if test "$(PACKAGE)" = "gettext-tools"; then \
	  for file in $(DISTFILES.common) Makevars.template; do \
	    rm -f $(DESTDIR)$(gettextsrcdir)/$$file; \
	  done; \
	else \
	  : ; \
	fi
uninstall-data-no:
uninstall-data-yes:
	catalogs='$(CATALOGS)'; \
	for cat in $$catalogs; do \
	  cat=`basename $$cat`; \
	  lang=`echo $$cat | sed -e 's/\.gmo$$//'`; \
	  for lc in LC_MESSAGES $(EXTRA_LOCALE_CATEGORIES); do \
	    rm -f $(DESTDIR)$(localedir)/$$lang/$$lc/$(DOMAIN).mo; \
	  done; \
	done

check: all

info dvi ps pdf html tags TAGS ctags CTAGS ID:

mostlyclean:
	rm -f remove-potcdate.sed
	rm -f stamp-poT
	rm -f core core.* $(DOMAIN).po $(DOMAIN).1po $(DOMAIN).2po *.new.po
	rm -fr *.o

clean: mostlyclean

distclean: clean
	rm -f Makefile Makefile.in POTFILES *.mo

maintainer-clean: distclean
	@echo "This command is intended for maintainers to use;"
	@echo "it deletes files that may require special tools to rebuild."
	rm -f stamp-po $(GMOFILES)

distdir = $(top_builddir)/$(PACKAGE)-$(VERSION)/$(subdir)
dist distdir:
	$(MAKE) update-po
	@$(MAKE) dist2
# This is a separate target because 'update-po' must be executed before.
dist2: $(DISTFILES)
	dists="$(DISTFILES)"; \
	if test "$(PACKAGE)" = "gettext-tools"; then \
	  dists="$$dists Makevars.template"; \
	fi; \
	if test -f $(srcdir)/ChangeLog; then \
	  dists="$$dists ChangeLog"; \
	fi; \
	for i in 0 1 2 3 4 5 6 7 8 9; do \
	  if test -f $(srcdir)/ChangeLog.$$i; then \
	    dists="$$dists ChangeLog.$$i"; \
	  fi; \
	done; \
	if test -f $(srcdir)/LINGUAS; then dists="$$dists LINGUAS"; fi; \
	for file in $$dists; do \
	  if test -f $$file; then \
	    cp -p $$file $(distdir); \
	  else \
	    cp -p $(srcdir)/$$file $(distdir); \
	  fi; \
	done

update-po: Makefile
	$(MAKE) $(DOMAIN).pot-update
	test -z "$(UPDATEPOFILES)" || $(MAKE) $(UPDATEPOFILES)
	$(MAKE) update-gmo

# General rule for creating PO files.

.nop.po-create:
	@lang=`echo $@ | sed -e 's/\.po-create$$//'`; \
	echo "File $$lang.po does not exist. If you are a translator, you can create it through 'msginit'." 1>&2; \
	exit 1

# General rule for updating PO files.

.nop.po-update:
	@lang=`echo $@ | sed -e 's/\.po-update$$//'`; \
	if test "$(PACKAGE)" = "gettext-tools"; then PATH=`pwd`/../src:$$PATH; fi; \
	tmpdir=`pwd`; \
	echo "$$lang:"; \
	test "$(srcdir)" = . && cdcmd="" || cdcmd="cd $(srcdir) && "; \
	echo "$${cdcmd}$(MSGMERGE) $$lang.po $(DOMAIN).pot -o $$lang.new.po"; \
	cd $(srcdir); \
	if $(MSGMERGE) $$lang.po $(DOMAIN).pot -o $$tmpdir/$$lang.new.po; then \
	  if cmp $$lang.po $$tmpdir/$$lang.new.po >/dev/null 2>&1; then \
	    rm -f $$tmpdir/$$lang.new.po; \
	  else \
	    if mv -f $$tmpdir/$$lang.new.po $$lang.po; then \
	      :; \
	    else \
	      echo "msgmerge for $$lang.po failed: cannot move $$tmpdir/$$lang.new.po to $$lang.po" 1>&2; \
	      exit 1; \
	    fi; \
	  fi; \
	else \
	  echo "msgmerge for $$lang.po failed!" 1>&2; \
	  rm -f $$tmpdir/$$lang.new.po; \
	fi

$(DUMMYPOFILES):

update-gmo: Makefile $(GMOFILES)
	@:

Makefile: Makefile.in.in $(top_builddir)/config.status POTFILES.in
	cd $(top_builddir) \
	  && CONFIG_FILES=$(subdir)/$@.in CONFIG_HEADERS= \
	       $(SHELL) ./config.status

force:

# Tell versions [3.59,3.63) of GNU make not to export all variables.
# Otherwise a system limit (for SysV at least) may be exceeded.
.NOEXPORT:
# Special Makefile rules for English message catalogs with quotation marks.

DISTFILES.common.extra1 = quot.sed boldquot.sed en@quot.header en@boldquot.header insert-header.sin Rules-quot

.SUFFIXES: .insert-header .po-update-en

en@quot.po-create:
	$(MAKE) en@quot.po-update
en@boldquot.po-create:
	$(MAKE) en@boldquot.po-update

en@quot.po-update: en@quot.po-update-en
en@boldquot.po-update: en@boldquot.po-update-en

.insert-header.po-update-en:
	@lang=`echo $@ | sed -e 's/\.po-update-en$$//'`; \
	if test "$(PACKAGE)" = "gettext"; then PATH=`pwd`/../src:$$PATH; GETTEXTLIBDIR=`cd $(top_srcdir)/src && pwd`; export GETTEXTLIBDIR; fi; \
	tmpdir=`pwd`; \
	echo "$$lang:"; \
	ll=`echo $$lang | sed -e 's/@.*//'`; \
	LC_ALL=C; export LC_ALL; \
	cd $(srcdir); \
	if $(MSGINIT) -i $(DOMAIN).pot --no-translator -l $$ll -o - 2>/dev/null | sed -f $$tmpdir/$$lang.insert-header | $(MSGCONV) -t UTF-8 | $(MSGFILTER) sed -f `echo $$lang | sed -e 's/.*@//'`.sed 2>/dev/null > $$tmpdir/$$lang.new.po; then \
	  if cmp $$lang.po $$tmpdir/$$lang.new.po >/dev/null 2>&1; then \
	    rm -f $$tmpdir/$$lang.new.po; \
	  else \
	    if mv -f $$tmpdir/$$lang.new.po $$lang.po; then \
	      :; \
	    else \
	      echo "creation of $$lang.po failed: cannot move $$tmpdir/$$lang.new.po to $$lang.po" 1>&2; \
	      exit 1; \
	    fi; \
	  fi; \
	else \
	  echo "creation of $$lang.po failed!" 1>&2; \
	  rm -f $$tmpdir/$$lang.new.po; \
	fi

en@quot.insert-header: insert-header.sin
	sed -e '/^#/d' -e 's/HEADER/en@quot.header/g' $(srcdir)/insert-header.sin > en@quot.insert-header

en@boldquot.insert-header: insert-header.sin
	sed -e '/^#/d' -e 's/HEADER/en@boldquot.header/g' $(srcdir)/insert-header.sin > en@boldquot.insert-header

mostlyclean: mostlyclean-quot
mostlyclean-quot:
	rm -f *.insert-header
