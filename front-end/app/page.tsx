import Aside from "./components/aside/aside";
import Section from "./components/section/section";
import { Montserrat } from 'next/font/google';


export default function Home() {
  return(
        <main className=" h-screen grid rounded-lg">
            <Aside/>
            <Section/>
        </main>
  ) ;
}
