import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Ventes : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Ventes"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Ventes;