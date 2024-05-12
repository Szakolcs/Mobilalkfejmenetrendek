# Mobilalkfejmenetrendek
Hello!

Chat gpt még abban a tudatban él hogy a master a fő branch, ezért ott találod meg a forráskódot. 
Az adatbázist nem sikerült összehozni, azzal ne is foglalkozz alapból 0 pont.
Megpróbálom ide belinkelni a pontokhoz tartozó sorokat, hogy könnyebben megtaláld.
**Ha bármi gond lenne írj bátran: discord - bluemanfaker** 

  -Firebase autentikáció meg van valósítva: Be lehet jelentkezni és regisztrálni:
    --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/LogIn.kt#L52
    --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/SignUp.kt#L55

    
  -Adatmodell definiálása (class vagy interfész formájában):
    --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/Transportation.java#L7


  -Legalább egy Lifecycle Hook használata a teljes projektben:
    --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/ScannerActivity.kt#L83


  -Legalább egy olyan androidos erőforrás használata, amihez kell android permission:
    --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/ScannerActivity.kt#L93

  -CRUD műveletek mindegyike megvalósult és az adatbázis műveletek a konvenciónakmegfelelően külön szálon történnek
    -U
      --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/ui/account/AccountFragment.kt#L53
    -D
      --https://github.com/Szakolcs/Mobilalkfejmenetrendek/blob/11466b72e83dc427e6ae62ddbace766cf144a054/Menetrendek2/app/src/main/java/com/example/menetrendek/ui/account/AccountFragment.kt#L84
