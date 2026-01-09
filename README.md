# GNSS Monitor - GNSS/GPS Positioning Mode Test Application

A comprehensive Android diagnostic tool for testing and monitoring GNSS/GPS positioning modes, network connectivity, and location services. This application provides detailed real-time information about GPS satellites, cellular network status, WiFi connections, and system location services.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Requirements](#requirements)
- [Installation](#installation)
- [Building from Source](#building-from-source)
- [Usage](#usage)
- [Permissions](#permissions)
- [Technical Details](#technical-details)
- [Architecture](#architecture)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License](#license)

## Overview

GNSS Monitor is a native Android application designed for developers, testers, and enthusiasts who need to analyze and debug location-based services on Android devices. Originally developed as an internal diagnostic tool, this app provides deep insights into:

- GNSS satellite tracking and status
- GPS positioning modes (single, track, last known)
- Network-based location services
- Cellular network information (GSM, LTE, 5G)
- WiFi connectivity and signal strength
- A-GPS/SUPL configuration
- System location service logs

## Features

### GNSS/GPS Tracking
- **Multiple Positioning Modes**
  - **Track Mode**: Continuous location updates
  - **Single Mode**: One-time location request
  - **Last Known**: Retrieve last cached location
- **Positioning Quality Settings**
  - High Accuracy
  - Balanced Power/Accuracy
  - Low Power
- **Satellite Information**
  - Real-time satellite vehicle (SV) status
  - Signal-to-noise ratio (C/N0) for each satellite
  - Satellite constellation (GPS, GLONASS, Galileo, BeiDou, QZSS, IRNSS, SBAS)
  - Color-coded constellation identification:
    - **Green** (Light): GPS satellites
    - **Blue** (Light): GLONASS satellites
    - **Red** (Light): BeiDou satellites
    - **Yellow** (Light): Galileo satellites
    - **Orange**: QZSS (Japanese) satellites
    - **Pink**: IRNSS (Indian) satellites
    - **White**: SBAS (augmentation) satellites
    - **Gray**: Unknown constellation
  - Usage flags (used in fix, ephemeris data, almanac data)
- **GNSS Status Monitoring**
  - Started, Searching, Acquiring, Tracking, Stopped states
  - First fix time tracking
  - Location accuracy metrics (horizontal, vertical, bearing)

### Network Monitoring
- **Cellular Information**
  - Real-time signal strength (dBm)
  - Network type detection (2G/3G/4G/5G)
  - Cell tower information (Physical Cell ID, PLMN)
  - Data activity monitoring
  - Roaming status
  - Multiple SIM support
- **WiFi Monitoring**
  - Connected network SSID
  - WiFi standard version (WiFi 4/5/6)
  - Signal quality
- **Network Connectivity**
  - Mobile data status
  - SUPL APN configuration
  - A-GPS enabled/disabled status

### Configuration & Diagnostics
- **Device Configuration Reader**
  - System properties (ro.* and persist.*)
  - Carrier configuration
  - Location service dump (dumpsys)
  - GMS location dump
- **Logging Service**
  - Background service for continuous logging
  - Location manager service logs
  - Environmental bearing logs
  - System log capture (requires root/system permissions)

### User Interface
- **Real-time Data Display**
  - Live location coordinates (latitude, longitude, altitude)
  - Speed and bearing information
  - Time stamps (GPS time, system time)
  - Provider information (GPS, Network, Fused)
- **Interactive Controls**
  - Provider selection (GPS, Network, Fused, Passive)
  - Mode selection (Track, Single, Last)
  - Quality selection (High Accuracy, Balanced, Low Power)
  - Aiding data deletion (cold start testing)
- **Menu Options**
  - Open location in maps
  - View device configuration
  - Access radio information
  - Launch location settings
  - Quick access to app info

## Screenshots

*Screenshots would go here showing the main interface, satellite view, network info, and configuration screens*

## Requirements

### Minimum Requirements
- **Android Version**: Android 12 (API 31) or higher
- **Target SDK**: Android 14 (API 34)
- **Architecture**: ARM or x86/x64 (any Android architecture)
- **Storage**: ~10 MB

### Recommended
- Android 13+ for best compatibility
- Real Android device (emulator has limited GPS/cellular features)
- GPS-enabled device for full GNSS testing
- Active SIM card for cellular network testing

### Optional (Enhanced Features)
- **Root Access**: For advanced system logs (READ_LOGS, DUMP permissions)
- **System App**: For protected permission access

## Installation

### Method 1: Install Pre-built APK
1. Download the latest APK from releases
2. Enable "Install from Unknown Sources" in Android settings
3. Install the APK
4. Grant required permissions when prompted

### Method 2: Android Studio
1. Clone or download this repository
2. Open the project folder in Android Studio
3. Sync Gradle files
4. Connect Android device or start emulator
5. Click Run ▶️ button

## Building from Source

### Prerequisites
- **Android Studio**: Latest stable version (Hedgehog or newer)
- **JDK**: Java 17 (use Android Studio's embedded JDK)
- **Android SDK**: API 31+ installed
- **Gradle**: 8.6+ (included with project)

### Build Steps

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd GNSSMonitor
   ```

2. **Open in Android Studio**
   - File → Open → Select GNSSMonitor directory
   - Wait for Gradle sync to complete

3. **Configure JDK (Important!)**
   - File → Settings → Build, Execution, Deployment → Build Tools → Gradle
   - Set "Gradle JDK" to **Embedded JDK** (jbr-17)
   - Apply and OK

4. **Build Debug APK**
   ```bash
   ./gradlew assembleDebug
   ```
   Output: `app/build/outputs/apk/debug/GNSSMonitor_v7.2.0-debug.apk`

5. **Build Release APK**
   ```bash
   ./gradlew assembleRelease
   ```
   Note: Release builds require signing configuration

6. **Install to Device**
   ```bash
   ./gradlew installDebug
   ```

### Build Variants
- **debug**: Development build with debugging enabled
  - Package: `com.gnssmonitor.debug`
  - Debuggable: Yes
- **release**: Production build with ProGuard minification
  - Package: `com.gnssmonitor`
  - Minified and optimized

## Usage

### Getting Started

1. **Launch the App**
   - Tap the GNSS Monitor icon

2. **Grant Permissions**
   - Location: Allow "All the time" for background tracking
   - Phone State: Required for cellular info
   - Notifications: For foreground service notifications

3. **Select Positioning Settings**
   - **Provider**: Choose GPS for satellite-based location
   - **Mode**: Select "track" for continuous updates
   - **Quality**: Choose "high accuracy" for best results

4. **Start Tracking**
   - Tap the **START** button
   - Wait for GNSS status to change from "SEARCHING" to "TRACKING"
   - Location data will appear when first fix is acquired

### Use Cases

#### Testing GPS Cold Start Performance
1. Tap **🗑️ DEL AD** button to delete aiding data
2. Select GPS provider, track mode, high accuracy
3. Tap START
4. Record time to first fix (displayed as "FixTime")

#### Monitoring Cellular Signal Quality
1. View the MOBILE section for signal strength
2. DATA section shows active data connection type
3. Cell signal table shows all visible cell towers
4. Signal strength in dBm (higher = better, e.g., -70 dBm is better than -110 dBm)

#### Understanding Satellite Display
The satellite table shows real-time information about all visible GNSS satellites:
- **PRN**: Pseudo-Random Noise code (satellite identifier)
- **C/N**: Carrier-to-Noise ratio in dB-Hz (signal strength, higher is better)
- **Freq**: Frequency band (L1, L5, E1, E5, etc.)
- **Flg**: Status flags
  - **A**: Almanac data available
  - **E**: Ephemeris data available
  - **U**: Used in position fix
- **Row Color**: Indicates satellite constellation
  - Light green = GPS (USA)
  - Light blue = GLONASS (Russia)
  - Light red = BeiDou (China)
  - Light yellow = Galileo (Europe)
  - Orange = QZSS (Japan)
  - Pink = IRNSS/NavIC (India)
  - White = SBAS (augmentation systems)

#### Comparing Location Providers
1. Try GPS provider → Note accuracy and fix time
2. Stop tracking
3. Try Network provider → Note accuracy and speed
4. Try Fused provider → Best of both worlds

#### Reading Device Configuration
1. Menu → Read Config
2. View tabs:
   - System RO: Read-only system properties
   - System RW: Read-write system properties
   - Dumpsys (Loc): Location service details
   - Dumpsys (GMS): Google Mobile Services location info

### Menu Options Explained

- **Read Config**: View system configuration and location service settings
- **Radio Info**: Open Android's hidden radio information screen
- **Network Settings**: Quick access to airplane mode and network settings
- **Location Settings**: Jump to system location settings
- **Open in Map**: Launch external map app with current location
- **App Info**: View this app's system settings
- **NFW Loc Info**: Open Network/Firmware location attribution info
- **Refresh**: Reload cellular and network information
- **Bluesky Check**: Launch background logging service

## Permissions

### Required Permissions (Runtime)
- `ACCESS_FINE_LOCATION`: GPS location access
- `ACCESS_COARSE_LOCATION`: Network-based location
- `ACCESS_BACKGROUND_LOCATION`: Location in background
- `READ_PHONE_STATE`: Cellular signal information
- `POST_NOTIFICATIONS`: Service notifications (Android 13+)

### Standard Permissions (Auto-granted)
- `ACCESS_LOCATION_EXTRA_COMMANDS`: GPS commands (e.g., delete aiding data)
- `ACCESS_NETWORK_STATE`: Check network connectivity
- `FOREGROUND_SERVICE`: Run foreground logging service
- `FOREGROUND_SERVICE_SPECIAL_USE`: Service type declaration

### Protected Permissions (Requires Root/System)
These permissions are declared but won't work on regular devices:
- `READ_LOGS`: Read system logs
- `DUMP`: Access dumpsys commands
- `PACKAGE_USAGE_STATS`: Package statistics
- `BATTERY_STATS`: Battery statistics
- `INTERACT_ACROSS_USERS`: Multi-user access

*The app functions fully without protected permissions - they're only for advanced debugging.*

## Technical Details

### Architecture

#### Design Pattern
- **MVVM** (Model-View-ViewModel) for configuration screens
- **Observer Pattern** for real-time data monitoring
- **Service-based** background logging

#### Key Components

**Activities**
- `MainActivity`: Primary user interface
- `ReadConfigActivity`: Configuration viewer with tabs

**Service**
- `BlueskyTrackService`: Foreground service for continuous logging
  - 3 log buffers (10,000 lines each)
  - IPC interface via Binder

**Observers**
- `LocationObserver`: GNSS/Location updates
- `TelephonyObserver`: Cellular network monitoring
- `WifiObserver`: WiFi status tracking
- `NetworkObserver`: Network connectivity
- `DataObserver`: Content observer for system settings
- `BlueskyLogObserver`: System log capture

**Data Models**
- `LocationHolder`: Encapsulates location data
- `CellInfoHolder`: Cell tower information (GSM/CDMA/LTE/NR/WCDMA/TDSCDMA)
- `GnssSvStatusHolder`: Satellite status information

**Utilities**
- `ConfigUtils`: Configuration reading and parsing
- `FormatUtils`: Data formatting helpers
- `IntentUtils`: Intent creation helpers
- `Constants`: Shared constants

#### Package Structure

The app uses a clean, organized package structure:

```
com.gnssmonitor/
├── MainActivity.java              # Main activity
├── ReadConfigActivity.java        # Configuration viewer activity
├── BlueskyTrackService.java       # Background logging service
├── data/                          # Data models and holders
│   ├── LocationHolder.java
│   ├── GnssSvStatusHolder.java
│   ├── CellInfoHolder.java
│   ├── CellInfoHolderFactory.java
│   └── ... (cell-specific holders)
├── observers/                     # Observer pattern implementations
│   ├── LocationObserver.java
│   ├── TelephonyObserver.java
│   ├── WifiObserver.java
│   ├── NetworkObserver.java
│   ├── DataObserver.java
│   ├── LogObserver.java
│   ├── BlueskyLogObserver.java
│   └── IObserver.java            # Observer interface
├── ui/config/                     # Configuration UI components
│   ├── ConfigPagerAdapter.java
│   ├── ConfigViewerFragment.java
│   └── ConfigViewModel.java
└── utils/                         # Utility classes
    ├── ConfigUtils.java
    ├── FormatUtils.java
    ├── HelperUtils.java
    ├── IntentUtils.java
    └── Constants.java
```

**Package naming:**
- **Application ID**: `com.gnssmonitor`
- **Debug variant**: `com.gnssmonitor.debug`
- **Base package**: `com.gnssmonitor.*`

### Satellite Color Coding

The app uses background colors to visually distinguish between different GNSS constellations in the satellite status table:

| Constellation | Color | Hex Code | Notes |
|--------------|-------|----------|-------|
| GPS | Light Green | `#d7f4db` | USA navigation system |
| GLONASS | Light Blue | `#e2f8f8` | Russian navigation system |
| BeiDou | Light Red | `#f7e3ea` | Chinese navigation system |
| Galileo | Light Yellow | `#fafad2` | European navigation system |
| QZSS | Orange | `#ffa07a` | Japanese regional system |
| IRNSS/NavIC | Pink | `#ff9f94` | Indian regional system |
| SBAS | White | `#ffffff` | Satellite-based augmentation (WAAS, EGNOS, MSAS) |
| Unknown | Gray | `#808080` | Unidentified constellation |

Colors are defined in `app/src/main/res/values/colors.xml` and can be customized.

### Technology Stack

- **Language**: Java 17
- **Build System**: Gradle 8.6
- **Android Gradle Plugin**: 8.4.1
- **Min SDK**: 31 (Android 12)
- **Target SDK**: 34 (Android 14)

#### Libraries
- AndroidX AppCompat
- AndroidX ConstraintLayout
- AndroidX Lifecycle (LiveData, ViewModel)
- Google Material Design Components
- Data Binding & View Binding

### Data Flow Architecture

The app uses an observer-based architecture to monitor and display real-time data from various Android system services. Here's how data flows through the application:

#### Location & GNSS Data Flow
```
┌─────────────────────┐
│ Android Location    │
│ Manager Service     │
└──────────┬──────────┘
           │ Location updates
           ↓
┌──────────────────────┐
│  LocationObserver    │ ← Listens for location changes
│  - Registers for     │   and GNSS status updates
│    updates           │
│  - Handles callbacks │
└──────────┬───────────┘
           │ Wraps raw data
           ↓
┌──────────────────────┐
│  LocationHolder      │ ← Encapsulates location data
│  - Lat/Lng/Alt       │   with formatting methods
│  - Accuracy          │
│  - Provider info     │
└──────────┬───────────┘
           │ Data Binding
           ↓
┌──────────────────────┐
│   UI Components      │ ← Automatic UI updates via
│   (TextViews, etc)   │   Android Data Binding
└──────────────────────┘
```

#### Satellite Data Flow
```
┌─────────────────────┐
│ GNSS Status API     │
│ (GnssStatus class)  │
└──────────┬──────────┘
           │ Satellite info (PRN, C/N0, flags)
           ↓
┌──────────────────────────┐
│  LocationObserver        │ ← Receives GnssStatus
│  - onGnssStatusChanged() │   callbacks from system
└──────────┬───────────────┘
           │ Creates holder for each satellite
           ↓
┌──────────────────────────┐
│  GnssSvStatusHolder      │ ← One instance per satellite
│  - getPrn()              │   Provides formatted data
│  - getCNRatio()          │   and constellation color
│  - getConstellationColor │
└──────────┬───────────────┘
           │ List of holders
           ↓
┌──────────────────────────┐
│  RecyclerView Adapter    │ ← Binds data to table rows
└──────────┬───────────────┘
           │ Renders to screen
           ↓
┌──────────────────────────┐
│  Satellite Table UI      │ ← Color-coded rows with
│  (ScrollView + Table)    │   satellite information
└──────────────────────────┘
```

#### Cellular Network Data Flow
```
┌─────────────────────┐
│ Telephony Manager   │
│ - Signal strength   │
│ - Cell info         │
└──────────┬──────────┘
           │ Phone state callbacks
           ↓
┌──────────────────────────┐
│  TelephonyObserver       │ ← Monitors cell signals,
│  - onSignalStrengthsChgd │   network type, roaming
│  - onCellInfoChanged     │
└──────────┬───────────────┘
           │ Creates typed holders
           ↓
┌──────────────────────────┐
│  CellInfoHolder          │ ← Factory creates specific
│  ├─ CellInfoGsmHolder    │   holder for cell type
│  ├─ CellInfoLteHolder    │   (GSM, LTE, NR, etc.)
│  ├─ CellInfoNrHolder     │
│  └─ ...                  │
└──────────┬───────────────┘
           │ List to RecyclerView
           ↓
┌──────────────────────────┐
│  Cell Signal Table UI    │ ← Displays signal strength,
│                          │   cell ID, frequency
└──────────────────────────┘
```

#### Network Connectivity Data Flow
```
┌─────────────────────┐       ┌─────────────────────┐
│  WiFi Manager       │       │ Connectivity Mgr    │
└──────────┬──────────┘       └──────────┬──────────┘
           │                             │
           │ WiFi callbacks              │ Network callbacks
           ↓                             ↓
┌──────────────────────┐       ┌──────────────────────┐
│   WifiObserver       │       │  NetworkObserver     │
└──────────┬───────────┘       └──────────┬───────────┘
           │                             │
           └─────────────┬───────────────┘
                         │ Consolidated network state
                         ↓
                  ┌──────────────┐
                  │  DataObserver │ ← Monitors settings
                  │  (Content     │   like AGNSS enable
                  │   Observer)   │
                  └──────┬────────┘
                         │ Updates UI
                         ↓
                  ┌──────────────────┐
                  │  UI Status Area  │ ← Shows WiFi SSID,
                  │  - WiFi status   │   mobile data state,
                  │  - Mobile data   │   AGNSS, roaming
                  │  - AGNSS state   │
                  └──────────────────┘
```

#### Background Logging Service
```
┌──────────────────────┐
│  MainActivity        │
│  (User starts logs)  │
└──────────┬───────────┘
           │ startService()
           ↓
┌──────────────────────────┐
│  BlueskyTrackService     │ ← Foreground Service
│  - Runs in background    │   for continuous logging
│  - 3 log buffers         │
│    (10k lines each)      │
└──────────┬───────────────┘
           │ Captures system logs
           ↓
┌──────────────────────────┐
│  BlueskyLogObserver      │ ← Reads logcat via
│  - LocationManager logs  │   Runtime.exec("logcat")
│  - Environment logs      │   (requires READ_LOGS)
│  - System logs           │
└──────────┬───────────────┘
           │ Stores in buffers
           ↓
┌──────────────────────────┐
│  IPC (Binder)            │ ← Service exposes interface
└──────────┬───────────────┘   for MainActivity to read
           │ Returns log data
           ↓
┌──────────────────────────┐
│  MainActivity displays   │ ← Shows recent logs
│  log contents            │   in scrollable view
└──────────────────────────┘
```

#### Key Design Patterns

1. **Observer Pattern**: All system monitoring uses observer classes that register for callbacks
2. **Holder Pattern**: Data models wrap raw Android objects with presentation logic
3. **Factory Pattern**: `CellInfoHolderFactory` creates appropriate holder types based on cell technology
4. **Service Pattern**: `BlueskyTrackService` runs independently for background logging
5. **Data Binding**: Automatic UI updates when model data changes (reduces boilerplate)

#### Update Frequency

- **Location updates**: Based on selected mode (1-5 seconds in track mode)
- **GNSS status**: ~1 second intervals when tracking
- **Cell signal**: ~3-5 seconds or when signal changes
- **WiFi status**: On connection state changes
- **Network state**: On connectivity changes

## Troubleshooting

### Build Issues

#### "Cannot find Java installation matching Java 17"
**Solution**:
1. Open Android Studio settings
2. Build, Execution, Deployment → Build Tools → Gradle
3. Set Gradle JDK to "Embedded JDK" (jbr-17)
4. Sync project

#### "class file for java.lang.MatchException not found"
**Cause**: Using system Java 21 which has Android compiler bugs

**Solution**: Configure to use Embedded JDK (see above)

#### Gradle sync fails
**Solution**:
1. File → Invalidate Caches → Invalidate and Restart
2. Delete `.gradle` folder in project directory
3. Rebuild

### Runtime Issues

#### GPS not acquiring fix
**Check**:
- Location services enabled in system settings
- Location permission granted (including background)
- Outdoors with clear sky view
- Airplane mode disabled
- Try deleting aiding data (🗑️ DEL AD button)

#### No cellular information showing
**Check**:
- READ_PHONE_STATE permission granted
- SIM card inserted and active
- Mobile data enabled
- Try Menu → Refresh

#### "Permission denied" for logs
**Expected**: READ_LOGS and DUMP are protected permissions
- Only work on rooted devices or system apps
- App functions normally without these features

#### Service stops in background
**Check**:
- Battery optimization disabled for this app
- Background location permission granted
- Notifications enabled (required for foreground service)

### Device-Specific Issues

#### Samsung Devices
- May require "Location accuracy" enabled
- Disable "Adaptive Battery" for this app

#### Pixel Devices
- Enable "Location Services" in Quick Settings
- Check "Google Location Accuracy"

#### Emulator
- GPS: Use emulator controls to set location
- Cellular: Limited simulation only
- Recommended: Test on real device

## FAQ

**Q: Is this app safe to use?**
A: Yes, the app only reads location and network data. No data is transmitted or uploaded anywhere.

**Q: Why does it need background location?**
A: To continue tracking when app is minimized. You can deny this if you only use the app in foreground.

**Q: Does it drain battery?**
A: Continuous GPS tracking does use battery. Stop tracking when not needed. The foreground service is battery-efficient.

**Q: Can I use this commercially?**
A: This is a diagnostic tool. Check your licensing requirements and customize as needed.

**Q: Why so many permissions?**
A: It's a diagnostic tool that monitors multiple subsystems. All permissions are optional except location.

**Q: Does it work offline?**
A: Yes, GPS works offline. Network location requires connectivity. Configuration reading works offline.

## Contributing

Contributions are welcome! Here's how you can help:

### Reporting Issues
- Use GitHub Issues
- Include Android version, device model
- Provide logcat output if possible
- Steps to reproduce

### Code Contributions
1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Follow existing code style
4. Test on multiple Android versions
5. Commit changes (`git commit -m 'Add amazing feature'`)
6. Push to branch (`git push origin feature/amazing-feature`)
7. Open Pull Request

### Code Style
- Java coding conventions
- 4-space indentation
- Clear variable names
- Comment complex logic
- Update README if adding features

## Changelog

### Version 7.2.0
- Current release
- Android 14 (API 34) support
- Java 17 compatibility
- Modern AndroidX libraries
- Removed vendor-specific dependencies
- Generic build configuration

## License

This project is provided as-is for educational and diagnostic purposes.

## Disclaimer

This application is a diagnostic tool and should be used responsibly. GPS and cellular monitoring can drain battery. Continuous location tracking should be used judiciously. The developers are not responsible for battery drain, data usage, or misuse of this application.

## Acknowledgments

- Android Location APIs
- AndroidX Libraries
- Material Design Components
- GNSS/GPS satellite systems (GPS, GLONASS, Galileo, BeiDou)

## Support

For issues, questions, or suggestions:
- Open an issue on GitHub
- Check existing issues for solutions
- Provide detailed information for bug reports

---

**Built with ❤️ for the Android developer community**

Last Updated: January 2026
Version: 7.2.0
