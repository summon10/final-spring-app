function showHidden() {
  //  document.getElementById("getByPassportPK").hidden = false;
    const select = document.getElementById("selection");
    const selectVal = select.options[select.selectedIndex].value;
    document.getElementById(selectVal).hidden=false;
}