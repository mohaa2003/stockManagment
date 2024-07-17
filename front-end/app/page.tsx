import Aside from "./components/aside/aside";
import Section from "./components/section/section";
import { Montserrat } from 'next/font/google';

const inter = Montserrat({ 
  subsets: ['latin'], 
  weight: ['400', '700'], 
  style: ['normal', 'italic'] 
});

export default function Home() {
  return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section/>
        </main>
  ) ;
}
