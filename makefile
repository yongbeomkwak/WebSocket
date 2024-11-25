terminate:
		sh Scripts/terminate.sh

reboot:
		make terminate
		sh Scripts/boot.sh