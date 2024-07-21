import type { Metadata } from "next";
import { Nunito } from 'next/font/google';
import "./globals.css";

const nunito = Nunito(
  {
    subsets: ["latin"] ,
    weight: ['300','400','500','600', '700'], 
    style: ['normal', 'italic'] 
  }
);

export const metadata: Metadata = {
  title: "Stock Managment",
  description: "Stock Managment Project to make the business managment",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={nunito.className} style={{fontWeight : 400}}>{children}</body>
    </html>
  );
}
