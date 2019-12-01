import java.util.Scanner;
	
	public class Calculadora {
		public static void main(String[] args) {		
		
		double a, b=0;
		double x, y, pt = 0; // x e y númeos que serão operacionados
		String s = "";
		char ch;
		int aux = 0;
		boolean hm = false; //pilha vazia ou nao
	
		Scanner teclado = new Scanner(System.in);	
		
			//Pilha para operandos e sinais
			Pilha<Double> op = new Pilha<Double>();
			Pilha<Character> pilha = new Pilha<Character>();
		
		
			System.out.println("Qual a equação?");
			String conta = teclado.nextLine();
		
			while (aux < conta.length()) {
				ch = conta.charAt(aux);
				
				// ver se é um número
				if (ch == '0' || ch == '1' || ch == '2' 
					|| ch == '3' || ch == '4'
					|| ch == '5' || ch == '6' 
					|| ch == '7' || ch == '8' || ch == '9') {
					
					while (aux+1 < conta.length() && (conta.charAt(aux+1) == '0' || conta.charAt(aux+1) == '1'
						|| conta.charAt(aux+1) == '2' || conta.charAt(aux+1) == '3' || conta.charAt(aux+1) == '4'
						|| conta.charAt(aux+1) == '5' || conta.charAt(aux+1) == '6' || conta.charAt(aux+1) == '7' 
						|| conta.charAt(aux+1) == '8' || conta.charAt(aux+1) == '9')) {
						
						s = s + ch;
						aux++;
						ch = conta.charAt(aux);
				}
					s = s + ch;
					s = s + '.';
			}
				
				//Ordem das prioridades...
				
			 if (ch == '(') { 
				pilha.push(ch);
			 }
			else if (ch == '+' || ch == '-') {
				while (!pilha.isEmpty() && (pilha.top() == '*' || pilha.top() == '/'
						|| pilha.top() == '^' || pilha.top() == '+' |pilha.top() == '-')) {
						s = s + pilha.pop();
				}
				pilha.push(ch);
			}
			 if (ch == '*' || ch == '/') {
				while (!pilha.isEmpty() && (pilha.top() == '*' || pilha.top() == '/' || pilha.top() == '^')) {
					s = s + pilha.pop();
				}
				pilha.push(ch);
			}
			 if (ch == '^') {
				while (!pilha.isEmpty() && pilha.top() == '^') {
					s = s + pilha.pop();
				}
				pilha.push(ch);
			}
			 if (ch == ')') {
				while (!pilha.isEmpty() && pilha.top() != '(') {
					s = s + pilha.pop();
				}
				if (pilha.top() == '(') {
					pilha.pop();
				}
			}
			aux++;
		}
		while (pilha.isEmpty() == false) {
			s = s + pilha.pop();
		}
		aux = 0;
		while (aux < s.length()) {
			ch = s.charAt(aux);
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' 
					|| ch == '6' || ch == '7' || ch == '8' || ch == '9') {
				while (aux+1 < s.length() && (s.charAt(aux+1) == '0' || s.charAt(aux+1) == '1'
						|| s.charAt(aux+1) == '2' || s.charAt(aux+1) == '3' || s.charAt(aux+1) == '4'
						|| s.charAt(aux+1) == '5' || s.charAt(aux+1) == '6' || s.charAt(aux+1) == '7' 
						|| s.charAt(aux+1) == '8' || s.charAt(aux+1) == '9')) {
					pilha.push(ch);
					aux++;
					ch = s.charAt(aux);
					hm = true;
				}
				if (hm) {
					pilha.push(ch);
					while (!pilha.isEmpty()) {
						a = (double) (pilha.pop() - '0');
						b += a * Math.pow(10, pt);
						pt++;
					}
					op.push(b);
					pt = 0;
					b = 0;
					hm = false;
				}
				else {
					a = (double) (ch - '0');
					op.push(a);
				}
			} 
			
			// operações
			
				if (ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '^') {
				y = op.pop();
				x = op.pop();
				if (ch == '+') {
					x = x+y;
				}				
				if (ch == '-') {
					x = x-y;
				}				
				if (ch == '/') {
					x = x/y;
				}				
				if (ch == '*') {
					x = x*y;
				}				
				if (ch == '^') {
					x = Math.pow(x, y);
				}
				
				op.push(x);
			}
				aux++;
		}
		
		System.out.println(op.pop());
	}
}