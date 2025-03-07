<details>

<summary> Reflection Modul 1 </summary>

## Reflection 1

Standar kode yang dipelajari di Modul 1 - Coding Standards adalah:

1. Clean Code

    a. Kode dengan Nama yang Bermakna:
Pastikan setiap variabel, fungsi, dan kelas memiliki nama yang jelas dan deskriptif agar kode mudah dipahami.

    b. Fungsi Kecil dan Spesifik:
Buatlah fungsi yang hanya melakukan satu tugas spesifik.

    c. Perbaiki Kode, Bukan Hanya Menambahkan Komentar:
Jangan gunakan komentar untuk memperbaiki kode yang buruk.

    d. Gunakan Abstract Interface:
Manfaatkan abstract interface untuk menyembunyikan implementasi objek. Hal ini meningkatkan fleksibilitas dan memungkinkan penggantian implementasi tanpa mengubah kode yang menggunakannya.



2. Secure Code

    a. Authentication
   
    Verifikasi harus dilakukan dari sisi server untuk mencegah serangan berbahaya. 

    b. Kebijakan password yang kuat

    Password sangatlah penting untuk mencegah akses tidak sah.

    c. Authorization

    Otorisasi memastikan hanya pengguna sah yang berwenang yang dapat mengakses hal tertentu.
    
    d. Session Management

    Sesi pengguna harus dikelola dengan aman untuk mencegah serangan seperti session hijacking.

    e. JSON Web Tokens (JWT)

   JWT digunakan untuk membawa klaim yang berisi informasi peran dan izin pengguna. Namun, penggunaan JWT harus disertai dengan validasi token yang tepat dan pemilihan algoritma yang aman untuk menghindari potensi risiko keamanan.

    f. Input Validation

   Data yang masuk ke dalam sistem harus divalidasi agar sesuai dengan tipe, format, dan rentang yang diharapkan untuk mencegah serangan injeksi dan manipulasi data. 



## Reflection 2

1. Menulis unit test adalah langkah penting untuk memastikan bahwa aplikasi kita berfungsi dengan baik. Idealnya sebuah class memiliki unit test yang cukup untuk setiap method dan skenario yang penting.
   100 % Code coverage tidak berarti bahwa kode kita bebas dari bug, karena mungkin masih ada kasus yang belum dicover atau kesalahan yang tidak terlihat.

2. Jika class Java baru memiliki prosedur setup dan instance variables yang sama, bisa menambah duplikasi yang tidak perlu. Kode yang duplikat bisa membuat kode lebih susah untuk dimaintain. Saran perbaikan saya adalah untuk menghindari duplikasi kode agar kode lebih bersih.
</details>

<details>
<summary> Reflection Modul 2</summary>

## Code quality issues dan cara perbaikannya

Pada kode saya isu yang ditemukan adalah penggunaan public identifier pada interface "ProductService.java". Startegi saya dalam memperbaikinya adalah dengan menghapus identifier public pada semua method interface tersebut.

PMD juga menemukan beberapa warning lain yakni `This utility class has a non-private constructor: src/main/java/id/ac/ui/cs/advprog/eshop/EshopApplication.java#L7`. Akan tetapi warning tersebut tidak saya fix karena class tersebut sebenarnya bukan utility class, saya menggunakan @SpringBootTest untuk beberapa unit test saya yang memerlukan instansiasi class `EshopApplication`.

Ada satu lagi warning yang tidak saya fix yakni `Unused import 'org.springframework.web.bind.annotation.*': src/main/java/id/ac/ui/cs/advprog/eshop/controller/ProductController.java#L8`. PMD beranggapan bahwa import tersebut tidak terpakai walaupun sebenarnya import tersebut saya pakai untuk membuat annotation mapping request HTTP.

## CI/CD workflows

Implementasi workflow saya sudah memenuhi CI/CD. Untuk CI, saya menggunakan github workflow untuk mengautomasi testing setiap kali kode di push ke repository. Untuk CD, saya menggunakan Koyeb yang akan mendeploy aplikasi otomatis setelah kode dipush dan berhasil melewati testing.
</details>

<details>
<summary> Reflection Modul 3</summary>

## Explain what principles you apply to your project!

1. Single Responsibility Principle (SRP) 

Single Responsibility Principle menyatakan bahwa setiap kelas atau modul harus memiliki satu alasan untuk berubah, artinya kelas tersebut hanya bertanggung jawab atas satu bagian dari fungsionalitas aplikasi.

Saya mengimplementasikan SRP dengan cara memisahkan kode yang bertanggung jawab ke product dan ke car, seperti memisahkan model, repository, service, dan controller untuk car dan product.
2. Open Close Principle (OCP)

Open-Closed Principle menyatakan bahwa kelas atau modul harus terbuka untuk ekstensi tetapi tertutup untuk modifikasi, sehingga kita bisa menambah fungsionalitas baru tanpa merubah kode yang ada.

Saya mengimplementasikan OCP dengan cara membuat interface untuk CarRepository dan ProductRepository agar kode bisa di ekstensi tanpa memodifikasi kode yang sudah ada.

3. Dependency Inversion Principle (DIP)

Dependency Inversion Principle (DIP) menyatakan bahwa  modul tingkat tinggi tidak bergantung pada modul tingkat rendah, keduanya harus bergantung pada abstraksi, dan rincian harus bergantung pada abstraksi, bukan sebaliknya.

Saya mengimplementasikan DIP dengan cara mengubah dependansi pada CarServiceImpl dan ProductServiceImpl agar bergantung ke interface CarRepository dan ProductRepository.

## Explain the advantages of applying SOLID principles to your project with examples.
1. Single Responsibility Principle (SRP)

Dengan mengaplikasikan SRP, kode saya dipastikan hanya bertanggung jawab atas satu hal. Jika dikemudian hari saya ingin membuat perubahan untuk hal spesifik, misalnya perubahan pada logika yang berkaitan dengan produk, saya hanya perlu mengubah kelas yang relevan tanpa mempengaruhi kode lainnya. Sebagai contoh, jika saya ingin mengubah logika penghapusan produk, maka saya hanya perlu mengubah kode di dalam ProductRepositoryImpl.

2. Open Close Principle (OCP)

Dengan mengaplikasikan OCP, kode saya akan lebih mudah untuk diperluas. Misalkan saya ingin menambahkan fitur baru untuk produk, maka saya hanya perlu menambahkan implementasi baru dari interface ProductRepository.

3. Dependency Inversion Principle (DIP)

Dengan mengaplikasikan DIP, jika saya ingin mengganti implementasi repository, saya hanya perlu menambahkan implementasi baru dari interface tanpa harus mengubah kode lama saya, sehingga mempermudah pekerjaan saya. Sebagai contoh, jika saya ingin mengubah implementasi kode di ProductRepository, saya cukup membuat kelas baru yang mengimplementasikan interface ProductRepository, tanpa harus mengubah implementasi kode di ProductService.

## Explain the disadvantages of not applying SOLID principles to your project with examples.

1. Tanpa SRP

Jika kelas memiliki lebih dari satu tanggung jawab, kode akan susah untuk dipahami, diubah, dan diuji. Sebagai contoh, jika saya menggabungkan implementasi ProductService dan CarService dalam satu kelas dengan berbagai tanggung jawab, saat saya ingin menambahkan fitur baru untuk produk, bisa jadi saya ikut merusak logika untuk mobil.

2. Tanpa OCP

Jika tidak menerapkan OCP, setiap kali saya ingin menambahkan fitur baru saya harus mengubah kode saya yang sudah ada, sehingga berisiko merusak fungsionalitas yang telah ada. Sebagai contoh, jika saya langsung memodifikasi ProductRepository, kode yang sudah ada sebelumnya mungkin harus diubah untuk menerima modifikasi baru. 

3. Tanpa DIP

Jika kode bergantung langsung ke implementasi dan bukan interface, akan terdapat masalah saat saya perlu mengganti implementasi tanpa merubah kode yang lain. Sebagai contoh, jika ProductService langsung bergantung ke ProductRepositoryImpl, saya tidak bisa mengubah kode di ProductRepositoryImpl tanpa ikut mengubah kode memiliki dependency. 
</details>