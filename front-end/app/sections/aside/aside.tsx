"use client"

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
import Link from "next/link";
import { usePathname } from "next/navigation";

const Aside : React.FC = () => {
    const pathname = usePathname();
    return(
        <aside className="h-full bg-slate-800 flex-col rounded-l-lg">
            <div className="topAside h-4/5 flex flex-col justify-center items-center content-center">
                <Link href={"/"} className={pathname === "/" ? "text-orange-400" : "text-white hover:text-orange-300 "}>
                    <MdHome className={pathname === "/" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={28} cursor={"pointer"} title="Principale"/>
                </Link>
                <Link href={"/ventes"} className={pathname === "/ventes" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <MdSell className={pathname === "/ventes" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={25} cursor={"pointer"} title="Les Ventes"/>
                </Link>
                <Link href={"/achats"} className={pathname === "/achats" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <ImCart className={pathname === "/achats" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={22} cursor={"pointer"} title="Les Achats"/>
                </Link>
                <Link href={"/fournisseurs"} className={pathname === "/fournisseurs" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <FaTruck className={pathname === "/fournisseurs" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={26} cursor={"pointer"} title="Les Fournisseurs"/>
                </Link>
                <Link href={"/clients"} className={pathname === "/clients" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <IoPerson className={pathname === "/clients" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={26} cursor={"pointer"}  title="Les Clients"/>
                </Link>
                <Link href={"/transactions"} className={pathname === "/transactions" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <FaMoneyBillTransfer className={pathname === "/transactions" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={25} cursor={"pointer"} title="Les Autres Transations"/>
                </Link>
                <Link href={"dettes"} className={pathname === "/dettes" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <MdOutlineMoneyOff className={pathname === "/dettes" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={26} cursor={"pointer"}  title="Les Dettes"/>
                </Link>
                <Link href={"statistiques"} className={pathname === "/statistiques" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <FaChartPie className={pathname === "/statistiques" ? "text-orange-400 border-b-2 border-b-orange-400 mb-5": "text-white hover:text-orange-300 mb-5"} size={24} cursor={"pointer"} title="Les Statistiques"/>
                </Link>
                <Link href={"/comptes"} className={pathname === "/comptes" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <IoCard className={pathname === "/comptes" ? "text-orange-400 border-b-2 border-b-orange-400": "text-white hover:text-orange-300"} size={26} cursor={"pointer"} title="les Comptes"/>
                </Link>
            </div>
            <div className="bottomAside h-1/5 flex flex-col justify-evenly items-center">
                <Link href={"/parametres"} className={pathname === "/parametres" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <MdSettings className={pathname === "/parametres" ? "text-orange-400 border-b-2 border-b-orange-400": "text-white hover:text-orange-300"} size={26} cursor={"pointer"}/>
                </Link>
                <Link href={"//"} className={pathname === "/" ? "text-orange-400" : "text-white hover:text-orange-300"}>
                    <MdLogout className={pathname === "//" ? "text-orange-400 border-b-2 border-b-orange-400": "text-white hover:text-orange-300"} size={26} cursor={"pointer"}/>
                </Link>
            </div>
        </aside>
    );
}

export default Aside;