# SIXTERN — Student Internship Experience & Evaluation App

**An Integrated Multi-Role Internship Management, Monitoring, and Evaluation Platform**

---

## 1. Application Overview

**SIXTERN** adalah merupakan aplikasi berbasis Android yang dirancang untuk mempermudah proses pengelolaan, pemantauan, dan evaluasi kegiatan magang mahasiswa. Aplikasi ini menghubungkan tiga peran utama, yaitu mahasiswa sebagai peserta magang, pembimbing lapangan dari perusahaan, serta pihak universitas atau program studi dalam satu sistem yang terintegrasi.

SIXTERN tidak hanya berfokus pada pendaftaran magang, tetapi juga mendukung proses pemantauan selama masa magang berlangsung, mulai dari pencatatan kehadiran, pengisian *daily logbook*, persetujuan aktivitas harian, hingga evaluasi kinerja dan rekapitulasi hasil magang.

## 2. Roadmap Features

- **GPS Attendance:** Sistem presensi harian berbasis geolokasi untuk memvalidasi titik kehadiran peserta magang di instansi/perusahaan.
- **Logbook & Activity:** Fitur pencatatan laporan kegiatan harian yang terhubung langsung dengan sistem antrean persetujuan.
- **Smart Notification & Reminder:** Pengingat otomatis bagi peserta untuk menghindari keterlambatan pengisian logbook.
- **Secure File Upload:** Kemudahan mengunggah dokumen pendukung, file tugas, atau laporan akhir secara langsung.

## 3. Arsitektur Multi-Role & Hak Akses

Sistem keamanan dan alur kerja aplikasi **SIXTERN** dibagi menjadi 3 peran utama (*multi-role*) agar koordinasi antar-instansi berjalan mulus.

| Role Pengguna | Tanggung Jawab Utama | Fitur / Hak Akses |
|---|---|---|
| *Mahasiswa* (*Peserta Magang*) | Menjalankan kewajiban harian dan melaporkan progres magang secara mandiri. | - Absensi GPS harian<br>- Input & Edit Logbook<br>- Upload dokumen/tugas<br>- Monitoring grafik progres pribadi |
| *Pembimbing Lapangan* (*Mentor Perusahaan*) | Mengawasi kinerja operasional harian peserta magang di tempat kerja. | - Approval / Revisi Logbook<br>- Validasi kehadiran GPS<br>- Input catatan evaluasi kerja<br>- Penilaian performa harian/mingguan |
| *Universitas / Prodi* (*Dosen / Admin Kampus*) | Memantau kredibilitas akademik dan status kelulusan program magang mahasiswa. | - Monitoring rekapitulasi nasional/prodi<br>- Supervisi progress chart mahasiswa<br>- Validasi dan sinkronisasi nilai akhir akademik |

## 4. Technology Stack

- **Programming Language:** Kotlin
- **Platform:** Android
- **UI Toolkit:** Jetpack Compose
- **Backend Services:** Firebase
- **Authentication:** Firebase Authentication
- **Database:** Cloud Firestore
- **File Storage:** Firebase Cloud Storage
- **Push Notifications:** Firebase Cloud Messaging
- **Location Services:** Android Location Services
- **Development Environment:** Android Studio


