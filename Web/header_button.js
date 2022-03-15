/*      STICKY HEADER     */  /*  el JavaScript fara les funcions de ensenya elements amagats i moure el header com a fixe al fer scroll*/

window.onscroll = function() {myFunction()};  /*  al fer scroll de finestra s'inicia la funcio MyFunction */

var header = document.getElementById("myHeader"); /*  Aqui li indiquem el header que voldrem moure*/
var sticky = header.offsetTop; /* .offsetTop propietat que retorne la distancia entre el elemnt i borde superior*/

function myFunction() {   /*  Aqui indiquem que fara exactament el myFunction */
  if (window.pageYOffset > sticky) { /* Aqui indiquem que si la part dalt de la pantalla arribar al element actual, i ens fagi sticky */
    header.classList.add("sticky"); /*  Aqiu li indiquem que la classe de element actual agregui un sticky*/
  } else {
    header.classList.remove("sticky");  /*  sino ens el borra*/
  }
}

/*      RESPONSIVE BUTTON       */

function myFunction1() {
  document.getElementById("myTopnav").classList.toggle("responsive"); /*  Indiquem l'element que ha de afectar la funcio 1 i que ara tindra el nom responsive*/
}

/*    USER OPTIONS    */

function myFunction2(){
  document.getElementById("MyUserList").classList.toggle("mostrar_opcio");  /*  Indiquem l'element que ha de afectar la funcio 2 i que ara tindra el nom mostrar opcio*/
}

  /*  Les dos funcions utilitzem per mostrar uns elements al fer click*/

  /*  A baix esta el codi per tornar els elements com estaven abans*/

window.onclick = function(event){ /*  al clica la pantalla ens fara funcio event*/
  if (!event.target.matches('h2')) {  /*  Si qualsevol cosa de la pantalla, menos element asignat(h2), es clicara */
    var dropdowns = document.getElementsByClassName("opcio_usuari");  /*  indiquem com es diuen elements  */
    var i;
    for (i = 0; i < dropdowns.length; i++) {  /*  Aqui fem un bucle que sempre que conte el mostra opcio l'element actual, es borra.*/
      var openDropdown = dropdowns[i];        /*  El mostrar_opcio ere el que feia que l'element sigui visible com que el borrem, l'element no s'ensenya, com abans  */
      if (openDropdown.classList.contains('mostrar_opcio')) {
        openDropdown.classList.remove('mostrar_opcio');
      }
    }
  }
  if (!event.target.matches('.icon i')) {   /*  Aquest if funciona de mateixa manera que el de dalt, pero amb element i variables diferents*/
    var dropdowns = document.getElementsByClassName("opcio");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('responsive')) {
        openDropdown.classList.remove('responsive');
      }
    }
  }
}