async function Enviar(){
    var nomeProduto = document.getElementById("nameInput").value;
    var departamentoProduto = document.getElementById("departamentoInput").value;
    var quantidadeProduto = document.getElementById("quantidadeInput").value;
    var precoProduto = document.getElementById("precoInput").value;
    var descrProduto = document.getElementById("descrInput").value

    var precoConvertido = parseFloat(precoProduto);

    if(isNaN(precoConvertido)){
        alert('Insira um valor válido no preço!')
        return;
    }
    var produto = {
        nome_produto: nomeProduto,
        departamento: departamentoProduto,
        quantidade: quantidadeProduto,
        preco: precoConvertido,
        descr: descrProduto
    };

    try{
        const response = await fetch('http://localhost:8081/cadastrar/produto', 
            { method: 'POST',headers: {
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto)
            });
        if(response.ok){
            alert('Cadastro concluido com sucesso!');
        }else {
            alert('Erro ao enviar o produto');
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao enviar o produto');
    }
}