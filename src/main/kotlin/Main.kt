import Models.InfoJogo
import Models.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o codigo do jogo: ")

    val busca = leitura.nextLine()

    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client.send(request, BodyHandlers.ofString())
    val json = response.body()
    val gson = Gson()
    val infoJogo = gson.fromJson(json, InfoJogo::class.java)

    var meuJogo:Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(infoJogo.info.title, infoJogo.info.thumb)
    }

    resultado.onFailure{
        println("Deu ruim")
    }

    resultado.onSuccess{
       println("Inserir descrição? s/n")

        val opcao = leitura.nextLine()

        if (opcao.equals("S", true)){
            println("Insira a descricao: ")
            val descricao = leitura.nextLine()
            meuJogo?.descricao = descricao
        }else{
            meuJogo?.descricao = meuJogo?.titulo
        }

        println(meuJogo)
    }

}