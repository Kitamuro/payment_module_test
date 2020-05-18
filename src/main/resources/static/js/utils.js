function validateSum(value) {
    document.getElementById("returnButton").disabled = value > orderDTO.amount;
}

function dateFormat(date) {
    return new Date(date).toLocaleString();
}
