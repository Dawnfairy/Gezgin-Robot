# Gezgin-Robot
Projenin ismi ”Gezgin Robot Projesi”dir.
Proje kapsamında belirli kurallara gore hareket eden bir robotun onundeki engelleri asarak istenen hedefe ulasmasını saglayan bir oyun tasarlanması beklenmektedir. Oyunda 2 adet problemin cozulmesi gerekmektedir. Problem 1 ve problem 2’de 4 adet adım bulunmaktadır. Projemizde Main, Izgara,
Engel, Robot ve EnKısaYolBul olmak uzere 5 farklı sınıf
vardır. Bize onceden verilen bir url icerisindeki text dosyasına
Main sınıfımızdaki UrlOku metoduyla eriserek ve dosyadaki
tasarıma gore ızgara, engel yapısını Izgara sınıfımızdaki
IzgaraOlustur metoduyla olusturarak Problem 1’deki adım 1
ve adım 2’yi gerceklestiriyoruz. Adım 3’te istenilen robotun
baslangıc ve hedef noktalarını ızgara uzerinde uygun karelere
Main sınıfımızdaki yapıcımızda rastgele belirliyoruz. Aynı
zamanda Main sınıfımızdaki Paint metoduyla tum ızgarayı
bulutlarla kaplayarak robotun tum ızgara dunyasını degil
sadece cevresini gormesini saglıyoruz. Boylelikle Problem 1
adım 3’teki tum isterleri gerceklestirmis bulunuyoruz. Adım
4’te robotun gectigi yolları ve cevresini ogrenerek hedefe
ulasmasını ve hedefe ulasırken daha once gectigi yollar belli
olacak sekilde her adımda yol uzerinde iz bırakması istenmektedir. Hedefe ulastıgında ise robotun ogrendigi yolların icinde baslangıc noktasından hedef noktasına giden en kısa yol ızgara uzerinde ayrıca cizdirilmelidir. Robotun gectigi yol
ları ogrenmesi icin Robot sınıfındaki koordinat degiskenlerini
Main sınıfında bulunan YolAra metoduna aktarıyoruz. Robotun hedef noktasına ulasana kadar engel bulunmayan yollardan
hareket etmesini ve ogrenilen yolları bir ArrayList’te tutarak
hafızasına almasını saglıyoruz. Olusturdugumuz ArrayList’i
EnKısaYolBul sınıfında kullanarak robotun ogrendigi yolların icinde baslangıc noktasından hedef noktasına giden en
kısa yolu buluyoruz. Buldugumuz yolu baska bir ArrayList’e
aktarıyoruz. Topladıgımız verileri kullanarak Main sınıfında
bulunan Paint metoduyla ekrana robotun ogrendigi, ustunden
gectigi yolları ve en kısa yolu cizdiriyoruz. Son olarak kodumuza ekledigimiz bir sayac ile toplam sure ve toplam gecilen
kare sayılarının verilerini Paint metodunda kullanarak ekrana
yazdırıyoruz. Boylelikle Problem 1 icin istenilen tum adımları
yerine getiriyoruz. Problem 2 adım 1 ve adım 2’de istenilenleri
yaparak kullanıcı tarafından girilen boyutlarda olusturdugumuz
ızgara uzerine engeller yerlestiriyoruz ve rastgele bir labirent
elde ediyoruz. Kullanıcının girdigi boyutları Main sınıfından
Engel sınıfının yapıcısına aktarıyoruz. Engel sınıfındaki Generate metodu ile labirentimizi, icerisinde cıkmaz sokaklar
da olacak sekilde rastgele olusturuyoruz. Adım 3’te bizden
istenilen labirentin giris ve cıkıs noktalarını, labirentin capraz
koselerinde olacak sekilde Engel sınıfımızdaki LabirentYol
metodunda belirliyoruz. Robot sınıfından aldıgımız robotun hareket koordinat verilerini LabirentYol metodunda kullanıyoruz. Bu metotta Stack yapısını kullanarak robot yanlıs
bir yola girdiginde dogru olarak tespit ettigi en son konuma gitmesini ve o konumdan itibaren yol aramaya devam
etmesini saglıyoruz. Aynı zamanda Main sınıfımızdaki Paint metoduyla tum ızgarayı bulutlarla kaplayarak robotun tum
labirent dunyasını bilmemesini de saglıyoruz. Boylelikle Problem 2 adım 3’u de yerine getiriyoruz. Adım 4’de LabirentYol
metodunda elde ettigimiz verileri kullanarak Paint metoduyla robotun cıkısa ulasmak icin izledigi yolu adım adım ızgara
uzerinde gosteriyoruz. Robot hedefe ulastıgında ise giris noktasından cıkıs noktasına giden yolu ızgara uzerinde ayrıca
cizdiriyoruz. Kodumuza ekledigimiz bir sayac ile toplam sure
ve toplam gecilen kare sayılarının verilerini Paint metodunda
kullanarak ekrana yazdırıyoruz. Son olarak elimizdeki robotun
gectigi yolları ve buldugu en kısa yolu bir text dosyası acarak
bu dosyaya yazdırıyoruz. Boylelikle projedeki tum adımları
yerine getiriyoruz.

PROBLEM 1:
Bu problemde sizden robotu ızgara (grid) üzerinde verilen hedefe engellere takılmadan en
kısa sürede ve en kısa yoldan ulaştırmanız beklenmektedir. Robotu tüm ızgarayı değil,
yalnızca gerekli yolları gezerek hedefe ulaşmasını sağlamalısınız.
Adım 1: Gerekli boyutlarda karesel bir ızgara alanı oluşturmanız gerekmektedir.
Adım 2: Izgara üzerine engeller ve duvarlar yerleştirilmelidir. Izgara boyutu, engel sayısı ve
engellerin konum bilgileri içeriği matris biçimindeki bir text dosyasından alınacaktır. Bu text
dosyasına önceden verilecek bir url adresinden (e-destek üzerinden paylaşılacaktır) uygulama
çalıştırıldığında otomatik olarak erişilerek dosyadaki tasarıma göre ızgara ve engel yapısı
oluşturulacaktır. Engeller birbirinden farklı tipteki nesnelerden oluşabilir. (Verilecek text
dosyasındaki 0 değeri engelsiz yollara; 1, 2, 3 değerleri ise üç farklı tipteki nesne için
engelleri temsil edecektir. Birbirinden farklı sayıda karesel alan işgal eden bu üç engel
nesnesinden 1 değerli nesne yalnızca 1 karelik alan 2 değerine sahip nesneler yanyana 2
kare içeren maksimum 2x2 lik; 3 değerine sahip nesneler ise yan yana 3 kare içeren
maksimum 3x3 lük kare alana Şekil 1’ deki gibi yerleştirilecektir. )
Adım 3: Robotun başlangıç ve hedef noktaları ızgara üzerindeki uygun (engel veya duvar
içermeyen) karelere rastgele belirlenmelidir. Robot başlangıçta tüm ızgara dünyasını
bilmemelidir, sadece bir adım sonraki kareleri görebilmelidir. Her adımda robotun
öğrenmediği kareler bulutlu (kapalı) olarak gösterilmeli, öğrenilen kareler ise açılarak ilgili
karelerde bulunan nesneye göre (engel, duvar, yol, vs.) belirtilmelidir.
Adım 4: Tüm bu bilgiler doğrultusunda, robotun hedefe en kısa sürede ulaşabileceği en kısa
yol, adım adım ızgara üzerinde gösterilmelidir. Robotun daha önce geçtiği yerler belli olacak
şekilde her adımda yol üzerinde iz bırakması gerekmektedir. Hedefe ulaşıldığında ise
başlangıç noktasından hedef konuma giden robota göre en kısa yol ızgara üzerinde ayrıca
çizdirilmelidir. Geçen toplam süre (sn cinsinden) ve kaç kare üzerinden geçildiği bilgileri
ekranda gösterilmelidir.

PROBLEM 2:
Bu problemde sizden robotu labirentteki çıkış noktasına ulaştırmanız beklenmektedir.
Adım 1: Kullanıcı tarafından istenilen boyutlarda bir ızgara oluşturmanız gerekmektedir.
Adım 2: Izgara üzerine 1 nolu tipte engeller yerleştirilerek labirent oluşturulmalıdır. Labirent
içerisinde mutlaka çıkışa ulaşamayan yollar bulunmalıdır.
Adım 3: Labirentin giriş ve çıkış noktaları dörtgen ızgaranın herhangi çapraz 2 köşesi olarak
belirlenmelidir. Robot başlangıçta labirenti bilmemelidir. Labirentte yanlış girilen bir yol
algılandığında robotun doğru olarak tespit ettiği en son konuma giderek buradan itibaren yol
aramaya devam etmesi gerekmektedir.
Adım 4: Tüm bu bilgiler doğrultusunda, robotun çıkışa ulaşmak için izlediği yol adım adım
ızgara üzerinde gösterilmelidir. Her adımda robotun daha önce geçtiği yollar üzerinde iz
bırakması gerekmektedir. Robot hedefe ulaştığında giriş noktasından çıkış noktasına giden
yol ızgara üzerinde çizilmelidir. Geçen toplam süre (sn cinsinden), kaç kare üzerinden
geçildiği bilgileri ekranda gösterilmelidir.
