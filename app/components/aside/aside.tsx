import { MdSettings } from "react-icons/md";
import { MdLogout } from "react-icons/md";
import { MdHome } from "react-icons/md";
import { ImCart } from "react-icons/im";
import { IoCard } from "react-icons/io5";
import { MdSell } from "react-icons/md";
import { IoPerson } from "react-icons/io5";
import { FaTruck } from "react-icons/fa";
import { FaChartPie } from "react-icons/fa";
import { MdOutlineMoneyOff } from "react-icons/md";
import { FaMoneyBillTransfer } from "react-icons/fa6";

const Aside : React.FC = () => {
    return(
        <aside className="h-full bg-slate-800 flex-col rounded-l-lg">
            <div className="topAside h-4/5 flex flex-col justify-center items-center content-center">
                <MdHome className="mb-5" color="white" size={28} cursor={"pointer"} title="Principale"/>
                <ImCart className="mb-5" color="white" size={22} cursor={"pointer"} title="Les Achats"/>
                <MdSell className="mb-5" color="white" size={25} cursor={"pointer"} title="Les Ventes"/>
                <FaMoneyBillTransfer className="mb-5" color="white" size={25} cursor={"pointer"} title="Les Autres Transations"/>
                <IoPerson className="mb-5" color="white" size={26} cursor={"pointer"}  title="Les Clients"/>
                <FaTruck className="mb-5" color="white" size={26} cursor={"pointer"} title="Les Fournisseurs"/>
                <FaChartPie className="mb-5" color="white" size={26} cursor={"pointer"} title="Les Statistiques"/>
                <MdOutlineMoneyOff className="mb-5" color="white" size={26} cursor={"pointer"}  title="Les Dettes"/>
                <IoCard color="white" size={26} cursor={"pointer"} title="les Comptes"/>
            </div>
            <div className="bottomAside h-1/5 flex flex-col justify-evenly items-center">
                <MdSettings color="white" size={26} cursor={"pointer"}/>
                <MdLogout color="white" size={26} cursor={"pointer"}/>
            </div>
        </aside>
    );
}

export default Aside;