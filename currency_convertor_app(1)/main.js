console.log("the file is working");

const populate = async (value, baseCurrency) => {
    let myStr = ""
    const apiKey = "cur_live_zqizB5A2LpOIhoZpo4MV5PULbKwNNWVCvf1W3lEM";
    const encodedApiKey = encodeURIComponent(apiKey);
    const url = `https://api.currencyapi.com/v3/latest?apikey=${encodedApiKey}&base_currency=${baseCurrency}`
    //https://api.currencyapi.com/v3/latest?apikey=
    //https://app.currencyapi.com : the site to use

    
    

    let response = await fetch(url);
    let rJson = await response.json();
    document.querySelector(".output").style.display = "block"

    for (let key of Object.keys(rJson["data"])) {
        myStr += `          
           <tr>
           <td>${key}</td>
          <td>${rJson["data"][key]["code"]}</td>
          <td>${rJson["data"][key]["value"] * value}</td>
          </tr>
           `
    }

    const tableBody = document.querySelector("tbody")
    tableBody.innerHTML = myStr;
}

const btn = document.querySelector(".btn")
btn.addEventListener("click",  (e) => {
    e.preventDefault()
    console.log("button is clicked")
    const value = parseInt(document.querySelector("input[name='quantity']").value);
    const baseCurrency = document.querySelector("select[name='currency']").value;

     populate(value, baseCurrency)
})

