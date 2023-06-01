package com.steve.scientificcalculatorii;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, bzero, bdot, bequal, bplus, bminus, bpi, btan, bcos, blog, bin, bc, bac, bb1, bb2, bsin, bfact, bsquare, bsqrt, binv, bdiv, bmul;

    TextView tvin, tvout;
    String pi = "3.14159265";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        bzero = findViewById(R.id.bzero);
        bdot = findViewById(R.id.bdot);
        bequal = findViewById(R.id.bequal);
        bplus = findViewById(R.id.bplus);
        bminus = findViewById(R.id.bminus);
        bpi = findViewById(R.id.bpi);
        btan = findViewById(R.id.btan);
        bcos = findViewById(R.id.bcos);
        blog = findViewById(R.id.blog);
        bin = findViewById(R.id.bin);
        bac = findViewById(R.id.bac);
        bc = findViewById(R.id.bc);
        bb1 = findViewById(R.id.bb1);
        bb2 = findViewById(R.id.bb2);
        bsin = findViewById(R.id.bsin);
        bfact = findViewById(R.id.bfact);
        bsquare = findViewById(R.id.bsquare);
        bsqrt = findViewById(R.id.bsqrt);
        binv = findViewById(R.id.binv);
        bdiv = findViewById(R.id.bdiv);
        bmul = findViewById(R.id.bmul);

        tvin = findViewById(R.id.tvin);
        tvout = findViewById(R.id.tvout);

        //on clickListener

        b1.setOnClickListener(v -> tvout.setText(tvout.getText() + "1"));


        b2.setOnClickListener(v -> tvout.setText(tvout.getText() + "2"));

        b3.setOnClickListener(v -> tvout.setText(tvout.getText() + "3"));

        b4.setOnClickListener(v -> tvout.setText(tvout.getText() + "4"));

        b5.setOnClickListener(v -> tvout.setText(tvout.getText() + "5"));

        b6.setOnClickListener(v -> tvout.setText(tvout.getText() + "6"));


        b7.setOnClickListener(v -> tvout.setText(tvout.getText() + "7"));

        b8.setOnClickListener(v -> tvout.setText(tvout.getText() + "8"));

        b9.setOnClickListener(v -> tvout.setText(tvout.getText() + "9"));


        bzero.setOnClickListener(v -> tvout.setText(tvout.getText() + "0"));

        bdot.setOnClickListener(v -> tvout.setText(tvout.getText() + "."));

        bac.setOnClickListener(v -> {
            tvout.setText("");
            tvin.setText("");
        });

        bc.setOnClickListener(v -> {
            String integer = tvout.getText().toString();
            integer = integer.substring(0, integer.length() -1);
            tvout.setText(integer);
        });

        bplus.setOnClickListener(v -> tvout.setText(tvout.getText() + "+"));

        bminus.setOnClickListener(v -> tvout.setText(tvout.getText() + "-"));

        bmul.setOnClickListener(v -> tvout.setText(tvout.getText() + "×"));

        bdiv.setOnClickListener(v -> tvout.setText(tvout.getText() + "÷"));
        bsqrt.setOnClickListener(v -> {
            String val = tvout.getText().toString();
            double r = Math.sqrt(Double.parseDouble(val));
            tvout.setText(String.valueOf(r));

        });

        bb1.setOnClickListener(v -> tvout.setText(tvout.getText() + ")"));

        bb2.setOnClickListener(v -> tvout.setText(tvout.getText() + "("));
        bpi.setOnClickListener(v -> {
            tvin.setText(bpi.getText());
            tvout.setText(tvout.getText() + pi);
        });

        bsin.setOnClickListener(v -> tvout.setText(tvout.getText() + "sin"));

        bcos.setOnClickListener(v -> tvout.setText(tvout.getText() + "cos"));

        btan.setOnClickListener(v -> tvout.setText(tvout.getText() + "tan"));
        binv.setOnClickListener(v -> tvin.setText(tvout.getText() + "^" + "(-1)"));

        bfact.setOnClickListener(v -> {
            int val = Integer.parseInt(tvout.getText().toString());
            int fact = factorial(val);
            tvout.setText(String.valueOf(fact));
            tvin.setText(val+"!");
        });

        bsquare.setOnClickListener(v -> {
            double d = Double.parseDouble(tvout.getText().toString());
            double square = d*d;
            tvout.setText(String.valueOf(square));
            tvin.setText(d+ "2");
        });
        bin.setOnClickListener(v -> tvout.setText(tvout.getText() + "in"));
        blog.setOnClickListener(v -> tvout.setText(tvout.getText() + "log"));
        bequal.setOnClickListener(v -> {
            String val = tvout.getText().toString();
            String replacedStr = val.replace('÷' , '/').replace('×' , '*');
            double result = equal(replacedStr);
            tvout.setText(String.valueOf(result));
            tvin.setText(val);
        });
    }

    // factorial function
    int factorial(int n) {

        return (n == 1 || n == 0) ? 1 : n * factorial(n - 1);
    }


    //equal function
    public static double equal(final String str){
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;

            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                    throw new RuntimeException("unexpected :" + (char) ch);
                return x;

            }
            double parseExpression() {

                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double xx = parseFactor();
                for (;;) {

                    if (eat('/')) xx /= parseFactor(); //division
                    else if (eat('*')) xx *= parseFactor(); //multiplication
                    else return xx;

                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); //unary plus
                if (eat('−')) return -parseFactor();

                    double x;
                    int startPos = this.pos;
                    if (eat('(')){ //parentheses
                        x = parseExpression();
                        eat(')');

                } else if ((ch >= '0'  && ch <= '9') || ch == '.') { //numbers
                       while ((ch >= '0'  && ch <= '9') || ch == '.') nextChar();

                       x = Double.parseDouble(str.substring (startPos ,this.pos));

                    } else if (ch >= 'a'  && ch <= 'z') { //functions
                        while (ch >= 'a'  && ch <= 'z') nextChar();
                        String func = str. substring(startPos,this.pos);
                        x = parseFactor();

                        if (func.equals("sqrt")) x = Math.sqrt(x);
                        else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                        else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                        else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                        else if (func.equals("log")) x = Math.log10(x);
                        else if (func.equals("in")) x = Math.log(x);

                        else throw new RuntimeException("Unknown Function:" + func);

                        }else {
                            throw new RuntimeException("Unexpected: " + (char)ch);

                    }
                    if (eat('^' )) x = Math.pow( x ,parseFactor()); //exponentiation
                    return x;

                }
        }.parse();
    }
}


