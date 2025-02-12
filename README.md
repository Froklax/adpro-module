## Reflection 1

### Clean Code Principles
- Menggunakan nama-nama yang deskriptif untuk variabel dan _method_ yang dibuat, seperti `updateProduct` dan `deleteProduct`.
- Setiap class memiliki fungsi masing-masing yang terpisah, contohnya `ProductController` yang menangani _request_ `HTTP`, `ProductService` menangani logika bisnis, dan `ProductRepository` menangani penyimpanan data produk.
- Membuat _method_ kecil yang melakukan suatu hal spesifik, contohnya `getProduct` yang fungsinya adalah untuk mengembalikan produk yang sesuai berdasarkan id produk.
- Menggunakan konvensi penamaan `Java`, seperti menggunakan `camelCase` untuk nama variabel dan _method_.

### Secure Coding Practices

- Menerapkan id produk yang susah ditebak dengan memberikan id random untuk setiap produk yang dibuat menggunakan `java.util.UUID`.
- Menggunakan _method_ `HTTP` yang sesuai, seperti `GET` untuk pengambilan dan `POST` untuk operasi yang mengubah `state`.
- Redirect setelah `POST`, seperti menggunakan `redirect:/product/list` untuk menghindari duplikat submisi dari _form_.
- Melakukan sanitasi _input_, form yang dibuat menggunakan `th:field` yang dimiliki `Thymeleaf`.

### Kesalahan Yang Ditemukan
- Melanggar prinsip _clean code_ dengan tidak menggunakan _method_ yang sudah dibuat sebelumnya, _method_ `deleteProduct` membuat `productData.iterator()` baru walaupun sudah ada _method_ `findAll` yang mengembalikan `productData.iterator()`. Saya memperbaikinya dengan mengganti `productData.iterator()` di dalam `deleteProduct` dengan _method_ `findAll` yang sudah dibuat.

## Reflection 2

1. Setelah saya membuat semua _unit test_ yang diminta, saya merasa lebih yakin dengan kebenaran kode saya meski masih perlu dipastikan semua kasus tercover. Menurut saya sendiri, jumlah unit test per kelas sebaiknya menyesuaikan kompleksitas fungsionalitas, dengan fokus pada fungsi utama kode dan _edge cases_. _Code coverage_ sendiri dapat digunakan sebagai metrik, tetapi 100% _coverage_ tidak menjamin kode bebas _error_ karena _code coverage_ hanya mengukur jumlah eksekusi baris bukan kebenaran logika semua skenario yang di tes. _Code coverage_ 90% yang mengecek semua skenario dan _edge cases_ dengan benar bisa lebih baik dari _code coverage_ 100% yang tidak mengecek hal tersebut.


2. Menurut saya sendiri, berdasarkan kode `CreateProductFunctionalTest.java` yang telah dibuat, terdapat potensi duplikasi kode jika saya membuat _functional test suite_ baru dengan setup yang serupa. Beberapa di antaranya adalah inisialisasi variabel `baseUrl`, `serverPort`, dan `endpoint` yang digunakan. Hal ini tentunya mengurangi kebersihan dan kualitas dari kode yang dibuat. Selain itu, untuk memverifikasi jumlah item dalam daftar produk, saya perlu memastikan produk telah dibuat terlebih dahulu. Hal ini sebenarnya sudah dilakukan dalam pengujian sebelumnya. Oleh karena itu, pengujian ini bisa digabungkan dengan test suite yang ada untuk mengurangi redundansi. Metode verifikasi saat ini bergantung pada perhitungan elemen `<td>` dalam `HTML`, yang rentan terhadap perubahan UI. Menurut saya, lebih baik menggunakan pendekatan yang lebih rentan terhadap perubahan, seperti pencarian elemen berdasarkan teks atau _selector_ yang lebih spesifik. Tetapi, penggabungan pengujian ini dapat membuat _method_ menjadi panjang dan sulit dibaca. Saran perbaikan dari saya sendiri, sebaiknya dilakukan pemisahan antara setup data awal dan metode pengujian yang lebih terstruktur. Dengan ini kode akan tetap terstruktur dan lebih fleksibel terhadap perubahan.