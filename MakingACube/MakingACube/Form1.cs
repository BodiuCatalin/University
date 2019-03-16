using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MakingACube
{
    public partial class Form1 : Form
    {
        int u1, v1, u2, v2; //ViewPort
        double a, b, c, d;
        int Tip;
        double Raza;
        double Alfa;

        class muchie { public int st, dr; }
        class varf
        {
            public double x, y, z;
            public varf(int X, int Y, int Z) { x = X; y = Y; z = Z; }
        }

        int u(double x) { return (int)((x - a) / (b - a) * (u2 - u1) + u1); }
        int v(double y) { return (int)((y - d) / (c - d) * (v2 - v1) + v1); }
        void ViewPort(int x1, int y1, int x2, int y2) { u1 = x1; v1 = y1; u2 = x2; v2 = y2; }
        void Window(double x1, double y1, double x2, double y2) { a = x1; d = y1; b = x2; c = y2; }

        private void button1_Click(object sender, EventArgs e)
        {
            ViewPort(50, 50, 650, 500);

            System.Drawing.Pen myPen;
            myPen = new System.Drawing.Pen(System.Drawing.Color.Chocolate);
            System.Drawing.Graphics formGraphics = this.CreateGraphics();

            System.IO.StreamReader Fc = new System.IO.StreamReader("C:/Users/Catalin/source/repos/WindowsFormsApp2/WindowsFormsApp2/FileCorp.txt");
            String Line = Fc.ReadLine();
            String[] Split = Line.Split(new Char[] { ' ', ',', '\t' });
            int n = Convert.ToInt32(Split[0]); varf[] V = new varf[n + 1];
            for (int i = 1; i <= n; i++)
            {
                Line = Fc.ReadLine();
                Split = Line.Split(new Char[] { ' ', ',', '\t' });
                int X = Convert.ToInt32(Split[0]);
                int Z = Convert.ToInt32(Split[1]);
                int Y = Convert.ToInt32(Split[2]) - 100; // y <--->z
                V[i] = new varf(X, Y, Z); // V V V !!
            }
            Line = Fc.ReadLine();
            Split = Line.Split(new Char[] { ' ', ',', '\t' });
            int m = Convert.ToInt32(Split[0]); muchie[] M = new muchie[m + 1];
            for (int j = 1; j <= m; j++) // Cit. Muchii
            {
                Line = Fc.ReadLine();
                Split = Line.Split(new Char[] { ' ', ',', '\t' });
                M[j] = new muchie();
                M[j].st = Convert.ToInt32(Split[0]);
                M[j].dr = Convert.ToInt32(Split[1]);
            }

            Line = Fc.ReadLine(); // Cit. Car. Pr. Tip, r, α
            Split = Line.Split(new Char[] { ' ', ',', '\t' });
            Tip = Convert.ToInt32(Split[0]);
            Raza = Convert.ToDouble(Split[1]);
            Alfa = Convert.ToDouble(Split[2]);
            Fc.Close();

            DefPr(Raza, Alfa); // 1=Par(r,α), 2=Persp.(d,q);
            a = b = Px(V[1]); c = d = Py(V[1]);
            for (int i = 2; i <= n; i++)
            {
                double px = Px(V[i]);
                if (px < a) a = px; else if (px > b) b = px;
                double py = Py(V[i]);
                if (py < c) c = py; else if (py > d) d = py;
            }
            Window(a, d, b, c); // Fereasta Reală

            for (int j = 1; j <= m; j++) // Desenare muchii
                formGraphics.DrawLine(myPen, u(Px(V[M[j].st])), v(Py(V[M[j].st])),
                u(Px(V[M[j].dr])), v(Py(V[M[j].dr])));
            myPen.Dispose();
            formGraphics.Dispose();


        }

        void DefPr(double r, double a) { Raza = r; Alfa = a; } // r=1; a=0.8; // = Pi/4
        double PrX(double x, double z) { return x + Raza * z * Math.Cos(Alfa); }
        double PrY(double y, double z) { return y + Raza * z * Math.Sin(Alfa); }
        double Px(varf P) { return PrX(P.x, P.z); }
        double Py(varf P) { return PrY(P.y, P.z); }



        public Form1()
        {


            InitializeComponent();
        }
    }
}
