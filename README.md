# BOYKA CLIENT — starter projektu (Fabric 1.21.4)

To jest autorski, kliencki szkielet wizualny dla Minecraft Java 1.21.4, a nie kopia interfejsu innego klienta.

## Co działa

- `BOYKA CLIENT` w lewym górnym rogu, bez logo.
- Panel pod `Right Shift`: własny ciemny motyw i przełączniki.
- Przełącznik branding HUD, zapisywany w `config/boyka-client.json`.
- Ustawienia lokalnej ścieżki pliku obrazu i opcji `Change Sky` w konfiguracji — jako przygotowanie do osobnego, kompatybilnego renderer'a nieba.
- Lokalna lista znajomych w konfiguracji.

## Celowo nie zawarto

- 2D ESP/hitboxów graczy, pokazywania pancerza przeciwników i znaczników przez ściany.
- chamsów, tracerów, odczytu encji poza normalną widocznością ani funkcji dających przewagę w PvP.

## Ważne o `Change Sky`

Zwykły płaski obraz pobrany z internetu nie jest gotową teksturą nieba Minecrafta. Działające niebo wymaga renderera nieba lub panoramy/cubemapy z odpowiednio przygotowanymi obrazami. Ten starter bezpiecznie zapisuje wybór pliku, ale nie udaje, że automatycznie renderuje dowolny obraz jako niebo.

## Budowanie

1. Zainstaluj JDK 21.
2. Otwórz katalog w IntelliJ IDEA albo wygeneruj Gradle Wrapper (`gradle wrapper`).
3. Uruchom `./gradlew build`.
4. Plik moda pojawi się w `build/libs`.

Wymagane są Fabric Loader i Fabric API zgodne z Minecraft 1.21.4.
