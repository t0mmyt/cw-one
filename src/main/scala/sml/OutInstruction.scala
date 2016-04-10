package sml

/**
  * OutInstruction
  *
  * @author Tom Taylor
  */
case class OutInstruction(label: String, opcode: String, register: Int) extends Instruction(label, opcode) {

  override def execute(m: Machine) =
    println(s"Output from r%d: %d".format(register, m.regs(register)))

  override def toString(): String = {
    super.toString + " output " + register + "\n"
  }
}

object OutInstruction {
  def apply(label: String, register: Int) =
    new OutInstruction(label, "out", register)
}