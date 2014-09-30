
!define APP_VENDOR "Ponup"
!define APP_NAME "TetrisKit"
!define APP_VERSION "1.0.1"
!define APP_FILENAME "${APP_NAME}-${APP_VERSION}-jar-with-dependencies.jar"

Name "${APP_VENDOR} ${APP_NAME}"
OutFile "${APP_NAME}-${APP_VERSION}-installer.exe"

# For removing Start Menu shortcut in Windows 7
RequestExecutionLevel user
WindowIcon Off

ShowInstDetails show
ShowUninstDetails show

Page directory # or InstallDir $DESKTOP
Page instfiles

Section
    SetOutPath $INSTDIR
	File "target\${APP_FILENAME}" # File directive after SetOutPath
    WriteUninstaller "$INSTDIR\uninstall.exe"
	CreateDirectory "$SMPROGRAMS\${APP_VENDOR}"
	CreateShortCut "$SMPROGRAMS\${APP_VENDOR}\${APP_NAME}.lnk" "$INSTDIR\${APP_FILENAME}"
    CreateShortCut "$SMPROGRAMS\${APP_VENDOR}\Uninstall.lnk" "$INSTDIR\uninstall.exe"
SectionEnd
 
Section "uninstall"
    Delete "$SMPROGRAMS\${APP_VENDOR}\${APP_NAME}.lnk"
	Delete "$SMPROGRAMS\${APP_VENDOR}\Uninstall.lnk"
	RmDir "$SMPROGRAMS\${APP_VENDOR}"
	Delete "$INSTDIR\${APP_FILENAME}"
    Delete "$INSTDIR\uninstall.exe"
	RmDir "$INSTDIR"
SectionEnd

UninstPage uninstConfirm
UninstPage instfiles
