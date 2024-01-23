// Exemplo de como adicionar uma classe à primeira célula do grid
var board = document.getElementById('board');
for (var i = 0; i < 6; i++) {
  for (var j = 0; j < 7; j++) {
    var cell = document.createElement('div');
    cell.classList.add('grid-cell');

    // Adiciona a classe 'first-cell' à primeira célula do grid
    if (j === 0 && i === 0) {
      cell.classList.add('first-cell');
    }

    board.appendChild(cell);
  }
}
