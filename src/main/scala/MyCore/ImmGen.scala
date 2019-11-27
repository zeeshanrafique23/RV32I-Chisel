package MyCore
import  chisel3._
import  chisel3.util._

class ImmGen extends Module{
    val io = IO(new Bundle{
        val pc     = Input(UInt(32.W))
        val instruction =Input(UInt(32.W))

        val iImm_out = Output(SInt(32.W))
        val uImm_out = Output(SInt(32.W))
        val sImm_out = Output(UInt(32.W))
        val sbImm_out= Output(UInt(32.W))
        val ujImm_out= Output(UInt(32.W))
    })

    val i_imm  = (io.instruction(31,20)).asSInt
    val u_imm  = (Cat(io.instruction(31,12))).asSInt
    val s_imm  = Cat(io.instruction(31,25),io.instruction(11,7))
    val sb_imm = Cat(io.instruction(31),io.instruction(7),io.instruction(30,25),io.instruction(11,8),0.U)
    val uj_imm = Cat(io.instruction(31),io.instruction(19,12),io.instruction(20),io.instruction(30,21),0.U)

    io.iImm_out  := (Cat(Fill(20,i_imm(11)),i_imm)).asSInt
    io.uImm_out  := ((Cat(Fill(12,u_imm(19)),u_imm))<<12.U).asSInt
    io.sImm_out  := Cat(Fill(20,s_imm(11)),s_imm)
    io.sbImm_out := io.pc + Cat(Fill(19,sb_imm(12)),sb_imm)
    io.ujImm_out := io.pc + Cat(Fill(11,uj_imm(20)),uj_imm)

}