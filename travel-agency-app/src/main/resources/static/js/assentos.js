var selectedSeatLabel = document.getElementById('selectedSeat');
var selectedSeat = '';

document.querySelectorAll('.seat input[type="checkbox"]').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        checkOnlyOne(this);
    });
});


function checkOnlyOne(currentCheckbox) {
    document.querySelectorAll('.seat input[type="checkbox"]').forEach(function(checkbox) {
        if (checkbox !== currentCheckbox) {
            checkbox.checked = false;
        } else if (checkbox.checked) {
            selectedSeat = checkbox.name;
            selectedSeatLabel.value =  checkbox.name;
        } else {
            selectedSeat = '';
            selectedSeatLabel.textContent = '';
        }
    });
}

