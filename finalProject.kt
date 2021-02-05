package final_project

import java.util.Scanner;
val Scanner = Scanner(System.`in`)

fun main()
{
    var KAI = dataPenumpang()
}

var databasePenumpang = mutableMapOf("aqsaka" to "aqsa123")

open class dataPenumpang(){

    init{
        println("SELAMAT DATANG DI KERETA API INDONESIA")
        println("\nSilahkan login terlebih dahulu")
        login()
    }

    public fun login()
    {
        var counter2 = 0
        var pass_counter = 0
        var i=0
        do {
            println("Silahkan masukkan username anda")
            var username2: String? = null
            username2 = readLine() ?: ""
            if(username2 in databasePenumpang){
                do {
                    println("Masukkan password")
                    var password = readLine()
                    if(password in databasePenumpang.values){
                        if(databasePenumpang[username2] == password){
                            println("Berhasil Login")
                            menu_utama("$username2")
                            pass_counter = 5
                            counter2 = 5
                        } else {
                            println("Password salah")
                        }
                    } else {
                        println("Password tidak tersedia")
                        pass_counter++
                    }
                    if (pass_counter == 3){
                        println("Ingin membuat akun baru? (y/n)")
                        var akun_baru = readLine()
                        while(akun_baru != "y" || akun_baru != "n"){
                            if(akun_baru == "y" || akun_baru == "n"){
                                break
                            }
                            print("Silahkan masukkan y/n: ")
                            akun_baru = readLine()
                            i++
                        }
                        if(akun_baru == "y"){
                            pass_counter = 4
                            counter2 = 4
                            membuatAkunBaru()
                        } else {
                            println("Silahkan coba setelah beberapa saat.")
                            System.exit(-1)
                        }
                    }
                } while (pass_counter <=3)
            } else {
                println("Username tidak tersedia")
            }
            counter2++
            if (counter2 == 3){
                println("Ingin membuat akun baru? (y/n)")
                var akun_baru = readLine()
                while(akun_baru != "y" || akun_baru != "n"){
                    if(akun_baru == "y" || akun_baru == "n"){
                        break
                    }
                    println("Silahkan masukkan y/n: ")
                    akun_baru = readLine()
                    i++
                }
                if(akun_baru == "y"){
                    pass_counter = 4
                    counter2 = 4
                    membuatAkunBaru()
                } else {
                    println("Silahkan coba setelah beberapa saat.")
                    System.exit(-1)
                }
            }
        } while (counter2 <= 3)

    }

    public fun membuatAkunBaru(){
        println("Membuat Akun Baru")
        println("Masukkan username baru: ")
        var username_baru: String = Scanner.nextLine()

        println("Masukkan password baru: ")
        var password_baru: String = Scanner.nextLine()

        // Meemasukkan username_baru dan password_baru ke dalam map
        // dengan username_baru sebagai key dan password_baru sebagai value

        databasePenumpang.put(username_baru, password_baru)
        login()
    }

    fun menu_utama(username2: String){
        println("\nSELAMAT DATANG, ${username2.toUpperCase()}")
        println("--- MENU UTAMA ---")
        println("1. Beli Tiket")
        println("2. Menyelesaikan transaksi")
        println("Tekan angka untuk memilih opsi: (1/2)")
        var pilihan_user = readLine()
        when(pilihan_user){
            "1" -> membeliTiket(username2)
            "2" -> logout(username2)
            else -> {
                println("Pilihan tidak tersedia.\nKembali ke Menu Utama")
                menu_utama(username2)
            }
        }
    }

    fun membeliTiket(username2: String){
        println("\nLAMAN PEMBELIAN TIKET\n")

        // BASIS DATA HARGA-HARGA TIKET
        var dewasa_weekend = 150000
        var anak_weekend = 75000
        var dewasa_weekday = 100000
        var anak_weekday = 50000

        // MENAMPILKAN HARGA TIKET
        println("             Dewasa           Anak(di bawah lima tahun)")
        println("Senin-jumat  Rp.$dewasa_weekday,00     Rp.$anak_weekend,00           ")
        println("Sabtu-Minggu Rp.$dewasa_weekend,00     Rp.$anak_weekday,00         ")

        // USER MENG-INPUT DATA PENUMPANG
        println("\nSilahkan masukkan data penumpang")

        var jumlah_dewasa = 0
        do{
            println("Jumlah penumpang dewasa? ")
            jumlah_dewasa = Scanner.nextInt()
        } while(jumlah_dewasa <0)

        var jumlah_anak = 0
        do{
            println("Jumlah penumpang anak?")
            jumlah_anak = Scanner.nextInt()
        } while(jumlah_anak <0)

        val total_penumpang = jumlah_anak + jumlah_dewasa


        val weekday = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat")

        // USER MEMILIH KURSI
        var ranges = 1..10
        var i: Int
        var j: Int
        var k: Int

        for(i in ranges){
            j = 10+i
            k = i
            j = 10+i
            println("$i" + "A " + "$j" + "A " + "$k"+ "B " + "$j"+"B")
        }

        var jarak = 1 .. total_penumpang
        var tempat_duduk_list= mutableListOf(" ")
        for(i in jarak){
            println("Silahkan pilih kursi anda:")
            var tempat_duduk= readLine()
            tempat_duduk_list.add(i, "$tempat_duduk")
        }

        // MEMILIH HARI BERANGKAT
        //println("Silahkan pilih hari berangkat: ")
        var lanjut: Boolean = false
        var hari_berangkat: String
        var total_harga = 0
        var totalHargaToString: String
        println("Silahkan pilih hari berangkat: ")
        do {

            hari_berangkat = Scanner.nextLine()

            if (hari_berangkat == "Senin" || hari_berangkat == "Selasa" || hari_berangkat == "Rabu" || hari_berangkat == "Kamis" || hari_berangkat == "Jumat") {
                total_harga = ((jumlah_dewasa * dewasa_weekday) + (jumlah_anak * anak_weekday))

                lanjut = true
            } else if (hari_berangkat == "Sabtu" || hari_berangkat == "Minggu") {
                total_harga = ((jumlah_dewasa * dewasa_weekend) + (jumlah_anak * anak_weekend))

                lanjut = true
            } else if (hari_berangkat == null){
                println("Pilihan tidak tersedia")
                println("Silahkan pilih hari berangkat: ")
            }

        } while(lanjut != true)
        totalHargaToString= total_harga.toString()
        println("\nDETAIL PEMESANAN\n")
        println("Nama Pelanggan: $username2")
        println("Jumlah Penumpang:" +
                "\n     Jumlah Dewasa = $jumlah_dewasa" +
                "\n     Jumlah Anak   = $jumlah_anak")
        println("Kursi Anda: ")
        for(i in jarak){
            println("${tempat_duduk_list.get(i)}")
        }
        println("Hari keberangkatan: $hari_berangkat")
        println("Jumlah yang harus dibayarkan: Rp. $totalHargaToString,00")

        println("\nPembelian berhasil")

        println("Tekan 1 untuk menyelesaikan transaksi")
        println("Tekan 2 untuk kembali ke menu utama")
        var akhiriTransaksi = readLine()
        when(akhiriTransaksi){
            "2" -> menu_utama(username2)
            "1" -> logout(username2)
            else -> {
                println("Kembali ke menu utama.")
                menu_utama(username2)
            }
        }
    }

    private fun logout(username2: String){
        println("Terima kasih telah menggunakan layanan kami $username2")
        System.exit(-1)
    }
}