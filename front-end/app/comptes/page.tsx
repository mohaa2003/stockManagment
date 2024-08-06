import Aside from "@/app/sections/aside/aside";
import Section from "../sections/section/section";

const Comptes : React.FC = () => {
    return(
        <main className="h-screen grid rounded-lg">
            <Aside/>
            <Section title={"Mes Comptes"} tableHeaders={[]} tableContent={[]}/>
        </main>
    );
}
export default Comptes;