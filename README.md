# WS-Factory

## Deskripsi Web Service

WS-Factory merupakan Web Service berbasis protokol SOAP yang menyediakan layanan milik pabrik coklat Willy Wangky. Web Service ini dibangun dengan menggunakan JAX WS dan di-deploy dengan menggunakan Apache Tomcat. Berikut ini merupakan beberapa service yang disediakan oleh layanan WS-Factory.

1. Bahan 
HTTP Request Method dan URL: `POST /ws-factory/bahan?wsdl`
Pada service ini tersedia beberapa method yang dapat digunakan, yaitu:
- getAllBahanInFactory: Mengambil daftar seluruh bahan yang ada pada pabrik.
- addBahan: Menambahkan bahan baru kepada pabrik.
- addStockBahan: Menambahkan stok bahan pada pabrik

2. Coklat
HTTP Request Method dan URL: `POST /ws-factory/coklat?wsdl`
Pada service ini tersedia beberapa method yang dapat digunakan, yaitu:
- getAllCoklatInFactory: Mengambil daftar seluruh bahan yang ada pada pabrik.
- addNewCoklat: Menambahkan coklat baru kepada pabrik.
- addStockCoklat: Menambahkan stok coklat pada pabrik.
- getResepBahan: Mengambil daftar bahan pada resep sebuah coklat.

3. Request 
HTTP Request Method dan URL: `POST /ws-factory/request?wsdl`
Pada service ini tersedia beberapa method yang dapat digunakan, yaitu:
- getAllRequest: Mengambil semua request penambahan add stock pada pabrik
- addNewRequest: Menambah request penambahan add stock coklat pada database pabrik
- getRequestStatus: Mengembalikan status request penambahan add coklat
- updateRequestStatus: Mengubah status request penambahan add coklat

4. Saldo 
HTTP Request Method dan URL: `POST /ws-factory/saldo?wsdl`
Pada service ini tersedia beberapa method yang dapat digunakan, yaitu:
- getListOfSaldo: Mengambil seluruh list riwayat saldo
- getCurrentSaldo: Mengambil saldo saat ini
- addSaldo: Mengupdate dan menambahkan saldo yang terbaru pada database

5. User 
HTTP Request Method dan URL: `POST /ws-factory/user?wsdl`
Pada service ini tersedia beberapa method yang dapat digunakan, yaitu:
- Register: Melakukan pendaftaran akun pada WS Factory
- Login: Melakukan login (pengecekan user pada basis data) pada WS Factory

## Deskripsi Basis Data

| Nama Tabel  | Atribut |
| ------------- | ------------- |
| bahan  | (idbahan, namabahan, jumlah, tanggalkadaluarsa)  |
| coklat  | (idcoklat, namacoklat, jumlah)  |
| requeststock  | (idrequeststock, idcoklat, jumlahrequest, status)  |
| resep  | (idcoklat, idbahan, jumlahbahan)  |
| saldo  | (idsaldo, saldo, saldo_timestamp)  |
| users  | (username, email, password)  |

## Pembagian Kerja SOAP

1. Tambah Coklat Baru dan Resep: 13518017
2. Tambah Request Add Stock Baru: 13518048
3. Mengembalikan Status Add Stock: 13518048
4. Melakukan Pembuatan Coklat dengan Bahan Pada Pabrik yang belum Kadaluarsa: 13518017
5. Mengambil Resep Coklat dari Basis Data: 13518017
6. Mengubah Status Permintaan Add Stock: 13518048
7. Menambah Saldo: 13518041
8. Mengembalikan Saldo: 13518041
9. Menambahkan Bahan dalam Gudang: 13518041
10. Validasi Login, dan Register dari WW Factory: 13518041





