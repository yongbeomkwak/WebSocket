terminate:
		sh Scripts/terminate.sh

reboot:
		make terminate
		./gradlew build
		sh Scripts/boot.sh
