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
