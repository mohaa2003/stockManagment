import Aside from "./sections/aside/aside";
import Section from "./sections/section/section";


export default function Home() {
  return(
        <main className=" h-screen grid rounded-lg">
            <Aside/>
            <Section title={"main"} tableHeaders={[]} tableContent={[]}/>
        </main>
  ) ;
}
